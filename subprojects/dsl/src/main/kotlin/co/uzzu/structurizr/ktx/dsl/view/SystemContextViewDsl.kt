package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
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
) : StaticViewInclusionScope<SystemContextView>(view)

@StructurizrDslMarker
class SystemContextViewExclusionScope
internal constructor(
    view: SystemContextView
) : StaticViewExclusionScope<SystemContextView>(view)
