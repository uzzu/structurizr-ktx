package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.Element
import com.structurizr.model.SoftwareSystemInstance
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.ComponentView
import com.structurizr.view.RelationshipView

abstract class ComponentViewBody(
    protected val view: ComponentView,
    private val config: Config
) {
    fun asText(): String = buildString {
        rankDirection()
        lineSeparator()

        view.elements.asSequence()
            .filter { it.element !is Component }
            .filter {
                when (val element = it.element) {
                    is SoftwareSystemInstance,
                    is ContainerInstance -> view.isElementInView(element)
                    else -> true
                }
            }
            .map { it.element }
            .forEach {
                elementExceptComponent(it)
                lineSeparator()
            }

        view.elements.asSequence()
            .filter { it.element is Component && it.element.parent is Container }
            .map {
                @Suppress("UNCHECKED_CAST")
                ParentElementPair(
                    it.element.parent as Container,
                    it.element as Component
                )
            }
            .groupBy { it.id }
            .forEach { (_, s) ->
                s.forEachIndexed { index, parentElementSet ->
                    if (index <= 0) {
                        beginScope(parentElementSet.parent)
                        lineSeparator()
                    }
                    indent()
                    component(parentElementSet.element)
                    lineSeparator()
                    if (index >= s.size - 1) {
                        endScope(parentElementSet.parent)
                        lineSeparator()
                    }
                }
            }

        view.relationships.forEach {
            relationshipView(it)
            lineSeparator()
        }
    }

    protected abstract fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection)
    protected abstract fun StringBuilder.beginScope(container: Container)
    protected abstract fun StringBuilder.endScope(container: Container)
    protected abstract fun StringBuilder.component(component: Component)
    protected abstract fun StringBuilder.elementExceptComponent(element: Element)
    protected abstract fun StringBuilder.relationshipView(relationshipView: RelationshipView)

    private fun StringBuilder.rankDirection() {
        val rankDirection = view.automaticLayout?.rankDirection ?: AutomaticLayout.RankDirection.TopBottom
        rankDirection(rankDirection)
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }

    private fun StringBuilder.indent() {
        append("  ")
    }
}
