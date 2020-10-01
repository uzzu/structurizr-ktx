@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Container
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ComponentView
import com.structurizr.view.ContainerView
import com.structurizr.view.DeploymentView
import com.structurizr.view.DynamicView
import com.structurizr.view.FilterMode
import com.structurizr.view.FilteredView
import com.structurizr.view.StaticView
import com.structurizr.view.Styles
import com.structurizr.view.SystemContextView
import com.structurizr.view.SystemLandscapeView
import com.structurizr.view.ViewSet
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ViewSetScope(
    private val views: ViewSet
) {
    /**
     * @see [ViewSet.createSystemLandscapeView]
     */
    fun SystemLandscapeView(
        key: String,
        description: String? = null,
        block: SystemLandscapeViewScope.() -> Unit = Any::doNothing
    ): SystemLandscapeView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createSystemLandscapeView(key, description)
            .apply { block(SystemLandscapeViewScope(this)) }
    }

    /**
     * @see [ViewSet.createSystemContextView]
     */
    fun SystemContextView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: SystemContextViewScope.() -> Unit = Any::doNothing
    ): SystemContextView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createSystemContextView(softwareSystem, key, description)
            .apply { block(SystemContextViewScope(this)) }
    }

    /**
     * @see [ViewSet.createContainerView]
     */
    fun ContainerView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: ContainerViewScope.() -> Unit = Any::doNothing
    ): ContainerView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createContainerView(softwareSystem, key, description)
            .apply { block(ContainerViewScope(this)) }
    }

    /**
     * @see [ViewSet.createComponentView]
     */
    fun ComponentView(
        container: Container,
        key: String,
        description: String? = null,
        block: ComponentViewScope.() -> Unit = Any::doNothing
    ): ComponentView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createComponentView(container, key, description)
            .apply { block(ComponentViewScope(this)) }
    }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        key: String,
        description: String? = null,
        block: DynamicViewScope.() -> Unit = Any::doNothing
    ): DynamicView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createDynamicView(key, description)
            .apply { block(DynamicViewScope(this)) }
    }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: DynamicViewScope.() -> Unit = Any::doNothing
    ): DynamicView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createDynamicView(softwareSystem, key, description)
            .apply { block(DynamicViewScope(this)) }
    }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        container: Container,
        key: String,
        description: String? = null,
        block: DynamicViewScope.() -> Unit = Any::doNothing
    ): DynamicView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createDynamicView(container, key, description)
            .apply { block(DynamicViewScope(this)) }
    }

    /**
     * @see [ViewSet.createDeploymentView]
     */
    fun DeploymentView(
        key: String,
        description: String? = null,
        block: DeploymentViewScope.() -> Unit = Any::doNothing
    ): DeploymentView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createDeploymentView(key, description)
            .apply { block(DeploymentViewScope(this)) }
    }

    /**
     * @see [ViewSet.createDeploymentView]
     */
    fun DeploymentView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: DeploymentViewScope.() -> Unit = Any::doNothing
    ): DeploymentView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.createDeploymentView(softwareSystem, key, description)
            .apply { block(DeploymentViewScope(this)) }
    }

    /**
     * @see [ViewSet.createFilteredView]
     */
    fun FilteredView(
        staticView: StaticView,
        key: String,
        description: String? = "",
        mode: FilterMode = FilterMode.Exclude,
        vararg tags: String
    ): FilteredView =
        views.createFilteredView(staticView, key, description, mode, *tags)

    /**
     * @see [ViewSet.createDefaultViews]
     */
    fun defaultViews() {
        views.createDefaultViews()
    }

    /**
     * @see [ViewSet.configuration]
     * @param block [StylesScope]
     */
    fun styles(block: StylesScope.() -> Unit): Styles {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return views.configuration.styles
            .apply { block(StylesScope(this)) }
    }
}
