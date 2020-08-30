package co.uzzu.structurizr.ktx.dsl.view

import com.structurizr.model.Container
import com.structurizr.view.ContainerView

/**
 * @see [ContainerView.externalSoftwareSystemBoundariesVisible]
 */
var ViewScope<ContainerView>.isExternalSoftwareSystemBoundariesVisible: Boolean
    get() = view.externalSoftwareSystemBoundariesVisible
    set(value) {
        view.externalSoftwareSystemBoundariesVisible = value
    }

/**
 * @see [ContainerView.addAllContainersAndInfluencers]
 */
fun InclusionScope<ContainerView>.allContainersAndInfluencers() {
    view.addAllContainersAndInfluencers()
}

/**
 * @see [ContainerView.addAllContainers]
 */
fun InclusionScope<ContainerView>.allContainers() {
    view.addAllContainers()
}

/**
 * @see [ContainerView.addAllInfluencers]
 */
fun InclusionScope<ContainerView>.allInfluencers() {
    view.addAllInfluencers()
}

/**
 * @see [ContainerView.addDependentSoftwareSystems]
 */
fun InclusionScope<ContainerView>.dependentSoftwareSystems() {
    view.addDependentSoftwareSystems()
}

/**
 * @see [ContainerView.add]
 */
fun InclusionScope<ContainerView>.container(
    container: Container,
    addRelationship: Boolean = true
) {
    view.add(container, addRelationship)
}

/**
 * @see [ContainerView.remove]
 */
fun ExclusionScope<ContainerView>.container(container: Container) {
    view.remove(container)
}
