package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Element
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView
import com.structurizr.view.Shape

abstract class DynamicViewBody(
    protected val view: DynamicView,
    private val config: Config
) {

    fun asText(): String = buildString {
        val elements = LinkedHashSet<Element>()
        view.relationships.flatMapTo(elements) {
            listOf(it.relationship.source, it.relationship.destination)
        }
        if (config.useSequenceDiagram) {
            elements.forEach {
                sequenceElement(it)
                lineSeparator()
            }
        } else {
            elements.forEach {
                element(it)
                lineSeparator()
            }
        }

        view.relationships.forEach {
            relationshipView(it)
            lineSeparator()
        }
    }

    protected abstract fun StringBuilder.element(element: Element)
    protected abstract fun StringBuilder.relationshipView(relationshipView: RelationshipView)

    private fun StringBuilder.sequenceElement(element: Element) {
        val style = view.viewSet.configuration.styles.findElementStyle(element)
        val sequenceType = style.shape.asSequenceType()
        val name = element.name
        val id = element.id
        val stereoType = element.asStereoType()
        val background = style.background
        append("""$sequenceType "$name" as $id <<$stereoType>> $background""")
    }

    private fun Shape.asSequenceType(): String = when (this) {
        Shape.Person -> "actor"
        else -> "participant"
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }
}
