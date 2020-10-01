package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Container
import com.structurizr.view.ContainerView

@StructurizrDslMarker
class ContainerViewScope(
    view: ContainerView
) : StaticViewScope<ContainerView, ContainerViewInclusionScope, ContainerViewExclusionScope>(
    view,
    ContainerViewInclusionScope(view),
    ContainerViewExclusionScope(view)
) {

    /**
     * @see [ContainerView.externalSoftwareSystemBoundariesVisible]
     */
    var isExternalSoftwareSystemBoundariesVisible: Boolean
        get() = view.externalSoftwareSystemBoundariesVisible
        set(value) {
            view.externalSoftwareSystemBoundariesVisible = value
        }
}

@StructurizrDslMarker
class ContainerViewInclusionScope
internal constructor(
    view: ContainerView
) : StaticViewInclusionScope<ContainerView>(view) {

    /**
     * @see [ContainerView.addAllContainersAndInfluencers]
     */
    fun allContainersAndInfluencers() {
        view.addAllContainersAndInfluencers()
    }

    /**
     * @see [ContainerView.addAllContainers]
     */
    fun allContainers() {
        view.addAllContainers()
    }

    /**
     * @see [ContainerView.addAllInfluencers]
     */
    fun allInfluencers() {
        view.addAllInfluencers()
    }

    /**
     * @see [ContainerView.addDependentSoftwareSystems]
     */
    fun dependentSoftwareSystems() {
        view.addDependentSoftwareSystems()
    }

    /**
     * @see [ContainerView.add]
     */
    fun container(
        container: Container,
        addRelationship: Boolean = true
    ) {
        view.add(container, addRelationship)
    }
}

@StructurizrDslMarker
class ContainerViewExclusionScope
internal constructor(
    view: ContainerView
) : StaticViewExclusionScope<ContainerView>(view) {

    /**
     * @see [ContainerView.remove]
     */
    fun container(container: Container) {
        view.remove(container)
    }
}
