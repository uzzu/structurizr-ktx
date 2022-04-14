package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Container
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
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

    override fun add(element: Element, addRelationships: Boolean) = when (element) {
        is Person -> person(element, addRelationships)
        is SoftwareSystem -> softwareSystem(element, addRelationships)
        is Container -> container(element, addRelationships)
        else -> throw IllegalArgumentException("A person or software system or container must be specified. element: $element")
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
