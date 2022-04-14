package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.SystemContextView

@StructurizrDslMarker
class SystemContextViewScope
internal constructor(
    view: SystemContextView
) : StaticViewScope<SystemContextView, SystemContextViewInclusionScope, SystemContextViewExclusionScope>(
    view,
    SystemContextViewInclusionScope(view),
    SystemContextViewExclusionScope(view)
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
class SystemContextViewInclusionScope
internal constructor(
    view: SystemContextView
) : StaticViewInclusionScope<SystemContextView>(view) {
    override fun add(element: Element, addRelationships: Boolean) = when (element) {
        is Person -> person(element, addRelationships)
        is SoftwareSystem -> softwareSystem(element, addRelationships)
        else -> throw IllegalArgumentException("A person or software system must be specified.\n element: $element")
    }
}

@StructurizrDslMarker
class SystemContextViewExclusionScope
internal constructor(
    view: SystemContextView
) : StaticViewExclusionScope<SystemContextView>(view)
