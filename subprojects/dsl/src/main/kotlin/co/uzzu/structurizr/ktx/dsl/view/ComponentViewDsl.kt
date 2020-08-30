package co.uzzu.structurizr.ktx.dsl.view

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.view.ComponentView

/**
 * @see [ComponentView.setExternalSoftwareSystemBoundariesVisible]
 */
var ViewScope<ComponentView>.isExternalContainerBoundariesVisible: Boolean
    get() = view.externalContainerBoundariesVisible
    set(value) {
        view.setExternalSoftwareSystemBoundariesVisible(value)
    }

/**
 * @see [ComponentView.addAllContainers]
 */
fun InclusionScope<ComponentView>.allContainers() {
    view.addAllContainers()
}

/**
 * @see [ComponentView.addAllComponents]
 */
fun InclusionScope<ComponentView>.allComponents() {
    view.addAllComponents()
}

/**
 * @see [ComponentView.addExternalDependencies]
 */
fun InclusionScope<ComponentView>.externalDependencies() {
    view.addExternalDependencies()
}

/**
 * @see [ComponentView.add]
 */
fun InclusionScope<ComponentView>.container(
    container: Container,
    addRelationship: Boolean = true
) {
    view.add(container, addRelationship)
}

/**
 * @see [ComponentView.add]
 */
fun InclusionScope<ComponentView>.component(
    component: Component,
    addRelationship: Boolean = true
) {
    view.add(component, addRelationship)
}

/**
 * @see [ComponentView.remove]
 */
fun ExclusionScope<ComponentView>.container(
    container: Container
) {
    view.remove(container)
}

/**
 * @see [ComponentView.remove]
 */
fun ExclusionScope<ComponentView>.component(
    component: Component
) {
    view.remove(component)
}
