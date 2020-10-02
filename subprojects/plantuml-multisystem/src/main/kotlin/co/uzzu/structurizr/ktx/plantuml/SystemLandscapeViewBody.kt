package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Enterprise
import com.structurizr.model.Location
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.ElementView
import com.structurizr.view.RelationshipView
import com.structurizr.view.SystemLandscapeView

abstract class SystemLandscapeViewBody(
    protected val view: SystemLandscapeView,
    private val config: Config
) {
    fun asText(): String = buildString {
        rankDirection()
        lineSeparator()

        val enterpriseBoundaryVisible =
            view.isEnterpriseBoundaryVisible &&
                view.elements.map(ElementView::getElement).any {
                    (it is Person && it.location == Location.Internal) ||
                        (it is SoftwareSystem && it.location == Location.Internal)
                }
        view.elements.asSequence()
            .map(ElementView::getElement)
            .filter { it is Person && it.location != Location.Internal }
            .map { it as Person }
            .sortedBy { it.name }
            .forEach {
                person(it)
                lineSeparator()
            }

        view.elements.asSequence()
            .map(ElementView::getElement)
            .filter { it is SoftwareSystem && it.location != Location.Internal }
            .map { it as SoftwareSystem }
            .sortedBy { it.name }
            .forEach {
                softwareSystem(it)
                lineSeparator()
            }

        if (enterpriseBoundaryVisible) {
            beginScope(view.model.enterprise)
            lineSeparator()
        }

        view.elements.asSequence()
            .map(ElementView::getElement)
            .filter { it is Person && it.location == Location.Internal }
            .map { it as Person }
            .sortedBy { it.name }
            .forEach {
                indentIf(enterpriseBoundaryVisible)
                person(it)
                lineSeparator()
            }

        view.elements.asSequence()
            .map(ElementView::getElement)
            .filter { it is SoftwareSystem && it.location == Location.Internal }
            .map { it as SoftwareSystem }
            .sortedBy { it.name }
            .forEach {
                indentIf(enterpriseBoundaryVisible)
                softwareSystem(it)
                lineSeparator()
            }

        if (enterpriseBoundaryVisible) {
            endScope(view.model.enterprise)
            lineSeparator()
        }

        view.relationships.forEach {
            relationshipView(it)
            lineSeparator()
        }
    }

    protected abstract fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection)
    protected abstract fun StringBuilder.person(person: Person)
    protected abstract fun StringBuilder.softwareSystem(softwareSystem: SoftwareSystem)
    protected abstract fun StringBuilder.beginScope(enterprise: Enterprise?)
    protected abstract fun StringBuilder.endScope(enterprise: Enterprise?)
    protected abstract fun StringBuilder.relationshipView(relationshipView: RelationshipView)

    private fun StringBuilder.rankDirection() {
        val rankDirection = view.automaticLayout?.rankDirection ?: AutomaticLayout.RankDirection.TopBottom
        rankDirection(rankDirection)
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }

    private fun StringBuilder.indentIf(satisfied: Boolean) {
        if (satisfied) {
            append("  ")
        }
    }
}
