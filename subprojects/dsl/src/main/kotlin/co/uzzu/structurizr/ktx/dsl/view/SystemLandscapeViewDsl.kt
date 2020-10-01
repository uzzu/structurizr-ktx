package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
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
) : StaticViewInclusionScope<SystemLandscapeView>(view)

@StructurizrDslMarker
class SystemLandscapeViewExclusionScope
internal constructor(
    view: SystemLandscapeView
) : StaticViewExclusionScope<SystemLandscapeView>(view)
