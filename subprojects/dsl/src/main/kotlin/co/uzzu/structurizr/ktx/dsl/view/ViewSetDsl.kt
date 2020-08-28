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

fun ViewSet.SystemLandscapeView(
    key: String,
    description: String? = null,
    block: ApplyBlock<SystemLandscapeView>? = null
): SystemLandscapeView =
    createSystemLandscapeView(key, description).applyIfNotNull(block)

fun ViewSet.SystemContextView(
    softwareSystem: SoftwareSystem,
    key: String,
    description: String? = null,
    block: ApplyBlock<SystemContextView>? = null
): SystemContextView =
    createSystemContextView(softwareSystem, key, description).applyIfNotNull(block)

fun ViewSet.ContainerView(
    softwareSystem: SoftwareSystem,
    key: String,
    description: String? = null,
    block: ApplyBlock<ContainerView>? = null
): ContainerView =
    createContainerView(softwareSystem, key, description).applyIfNotNull(block)

fun ViewSet.ComponentView(
    container: Container,
    key: String,
    description: String? = null,
    block: ApplyBlock<ComponentView>? = null
): ComponentView =
    createComponentView(container, key, description).applyIfNotNull(block)

fun ViewSet.DynamicView(
    key: String,
    description: String? = null,
    block: ApplyBlock<DynamicView>? = null
): DynamicView =
    createDynamicView(key, description).applyIfNotNull(block)

fun ViewSet.DynamicView(
    softwareSystem: SoftwareSystem,
    key: String,
    description: String? = null,
    block: ApplyBlock<DynamicView>? = null
): DynamicView =
    createDynamicView(softwareSystem, key, description).applyIfNotNull(block)

fun ViewSet.DynamicView(
    container: Container,
    key: String,
    description: String? = null,
    block: ApplyBlock<DynamicView>? = null
): DynamicView =
    createDynamicView(container, key, description).applyIfNotNull(block)

fun ViewSet.DeploymentView(
    key: String,
    description: String? = null,
    block: ApplyBlock<DeploymentView>? = null
): DeploymentView =
    createDeploymentView(key, description).applyIfNotNull(block)

fun ViewSet.DeploymentView(
    softwareSystem: SoftwareSystem,
    key: String,
    description: String? = null,
    block: ApplyBlock<DeploymentView>? = null
): DeploymentView =
    createDeploymentView(softwareSystem, key, description).applyIfNotNull(block)

fun ViewSet.FilteredView(
    staticView: StaticView,
    key: String,
    description: String? = null,
    mode: FilterMode,
    vararg tags: String,
    block: ApplyBlock<FilteredView>? = null
): FilteredView =
    createFilteredView(staticView, key, description, mode, *tags).applyIfNotNull(block)

fun ViewSet.styles(block: ApplyBlock<Styles>) =
    configuration.styles.apply(block)
