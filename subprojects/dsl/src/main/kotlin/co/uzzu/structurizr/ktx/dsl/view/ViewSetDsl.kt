@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
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

class ViewSetScope(
    private val views: ViewSet
) {
    /**
     * @see [ViewSet.createSystemLandscapeView]
     */
    fun SystemLandscapeView(
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<SystemLandscapeView>>? = null
    ): SystemLandscapeView =
        views.createSystemLandscapeView(key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createSystemContextView]
     */
    fun SystemContextView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<SystemContextView>>? = null
    ): SystemContextView =
        views.createSystemContextView(softwareSystem, key, description)
            .apply { block?.let { ViewScope<SystemContextView>(this).apply(it) } }

    /**
     * @see [ViewSet.createContainerView]
     */
    fun ContainerView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<ContainerView>>? = null
    ): ContainerView =
        views.createContainerView(softwareSystem, key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createComponentView]
     */
    fun ComponentView(
        container: Container,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<ComponentView>>? = null
    ): ComponentView =
        views.createComponentView(container, key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<DynamicView>>? = null
    ): DynamicView =
        views.createDynamicView(key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<DynamicView>>? = null
    ): DynamicView =
        views.createDynamicView(softwareSystem, key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createDynamicView]
     */
    fun DynamicView(
        container: Container,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<DynamicView>>? = null
    ): DynamicView =
        views.createDynamicView(container, key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createDeploymentView]
     */
    fun DeploymentView(
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<DeploymentView>>? = null
    ): DeploymentView =
        views.createDeploymentView(key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createDeploymentView]
     */
    fun DeploymentView(
        softwareSystem: SoftwareSystem,
        key: String,
        description: String? = null,
        block: ApplyBlock<ViewScope<DeploymentView>>? = null
    ): DeploymentView =
        views.createDeploymentView(softwareSystem, key, description)
            .apply { block?.let { ViewScope(this).apply(it) } }

    /**
     * @see [ViewSet.createFilteredView]
     */
    fun FilteredView(
        staticView: StaticView,
        key: String,
        description: String? = null,
        mode: FilterMode,
        vararg tags: String,
        block: ApplyBlock<FilteredView>? = null
    ): FilteredView =
        views.createFilteredView(staticView, key, description, mode, *tags)
            .applyIfNotNull(block)

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
    fun styles(block: ApplyBlock<StylesScope>): Styles =
        views.configuration.styles.apply { StylesScope(this).apply(block) }
}
