package co.uzzu.structurizr.ktx.dsl.view

import com.structurizr.view.SystemContextView

/**
 * @see [SystemContextView.isEnterpriseBoundaryVisible]
 */
var ViewScope<SystemContextView>.isEnterpriseBoundaryVisible: Boolean
    get() = view.isEnterpriseBoundaryVisible
    set(value) {
        view.isEnterpriseBoundaryVisible = value
    }
