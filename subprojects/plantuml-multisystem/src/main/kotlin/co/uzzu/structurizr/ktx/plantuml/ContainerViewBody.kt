package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Container
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.ContainerView
import com.structurizr.view.RelationshipView

abstract class ContainerViewBody(
    protected val view: ContainerView,
    private val config: Config
) {
    fun asText(): String = buildString {
        rankDirection()
        lineSeparator()

        view.elements.asSequence()
            .filter { it.element is Container && it.element.parent is SoftwareSystem }
            .map {
                @Suppress("UNCHECKED_CAST")
                ParentElementPair(
                    it.element.parent as SoftwareSystem,
                    it.element as Container
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
                    container(parentElementSet.element)
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
    protected abstract fun StringBuilder.beginScope(softwareSystem: SoftwareSystem)
    protected abstract fun StringBuilder.endScope(softwareSystem: SoftwareSystem)
    protected abstract fun StringBuilder.container(container: Container)
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
