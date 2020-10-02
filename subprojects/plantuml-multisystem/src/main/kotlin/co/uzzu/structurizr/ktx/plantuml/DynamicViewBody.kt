package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Element
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView

abstract class DynamicViewBody(
    protected val view: DynamicView,
    private val config: Config
) {
    private val useSequenceDiagram: Boolean
        get() = config.sequenceDiagramConfigs.any { it.key == view.key }

    fun asText(): String = buildString {
        if (!useSequenceDiagram) {
            rankDirection()
            lineSeparator()
        }
        if (useSequenceDiagram) {
            hideStereoType()
            lineSeparator()
        }

        val elements = LinkedHashSet<Element>()
        view.relationships.flatMapTo(elements) {
            listOf(it.relationship.source, it.relationship.destination)
        }
        if (useSequenceDiagram) {
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

    protected abstract fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection)
    protected abstract fun StringBuilder.sequenceElement(element: Element)
    protected abstract fun StringBuilder.element(element: Element)
    protected abstract fun StringBuilder.relationshipView(relationshipView: RelationshipView)

    private fun StringBuilder.rankDirection() {
        val rankDirection = view.automaticLayout?.rankDirection ?: AutomaticLayout.RankDirection.TopBottom
        rankDirection(rankDirection)
    }

    private fun StringBuilder.hideStereoType() {
        val sequenceDiagramConfig = requireNotNull(config.sequenceDiagramConfigs[view.key])
        if (sequenceDiagramConfig.hideStereoType) {
            append("hide stereotype")
        } else {
            append("show stereotype")
        }
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }
}
