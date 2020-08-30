package co.uzzu.structurizr.ktx.dsl.view

import com.structurizr.view.SystemLandscapeView

/**
 * @see [SystemLandscapeView.isEnterpriseBoundaryVisible]
 */
var ViewScope<SystemLandscapeView>.isEnterpriseBoundaryVisible: Boolean
    get() = view.isEnterpriseBoundaryVisible
    set(value) {
        view.isEnterpriseBoundaryVisible = value
    }
