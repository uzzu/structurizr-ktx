package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.SystemContextView
import com.structurizr.view.SystemLandscapeView

@StructurizrDslMarker
class SystemLandscapeViewScope
internal constructor(
    view: SystemLandscapeView
) : StaticViewScope<SystemLandscapeView, SystemLandscapeViewInclusionScope, SystemLandscapeViewExclusionScope>(
    view,
    SystemLandscapeViewInclusionScope(view),
    SystemLandscapeViewExclusionScope(view)
) {
    /**
     * @see [SystemContextView.isEnterpriseBoundaryVisible]
     */
    var isEnterpriseBoundaryVisible: Boolean
        get() = view.isEnterpriseBoundaryVisible
        set(value) {
            view.isEnterpriseBoundaryVisible = value
        }
}

@StructurizrDslMarker
class SystemLandscapeViewInclusionScope
internal constructor(
    view: SystemLandscapeView
) : StaticViewInclusionScope<SystemLandscapeView>(view) {
    override fun add(element: Element, addRelationships: Boolean) = when (element) {
        is Person -> person(element, addRelationships)
        is SoftwareSystem -> softwareSystem(element, addRelationships)
        else -> throw IllegalArgumentException("A person or software system must be specified.")
    }
}

@StructurizrDslMarker
class SystemLandscapeViewExclusionScope
internal constructor(
    view: SystemLandscapeView
) : StaticViewExclusionScope<SystemLandscapeView>(view)
