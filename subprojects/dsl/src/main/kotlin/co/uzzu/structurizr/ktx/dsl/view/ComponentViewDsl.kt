package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.ComponentView

@StructurizrDslMarker
class ComponentViewScope
internal constructor(
    view: ComponentView
) : StaticViewScope<ComponentView, ComponentViewInclusionScope, ComponentViewExclusionScope>(
    view,
    ComponentViewInclusionScope(view),
    ComponentViewExclusionScope(view)
) {
    /**
     * @see [ComponentView.setExternalSoftwareSystemBoundariesVisible]
     */
    var isExternalContainerBoundariesVisible: Boolean
        get() = view.externalContainerBoundariesVisible
        set(value) {
            view.setExternalSoftwareSystemBoundariesVisible(value)
        }
}

@StructurizrDslMarker
class ComponentViewInclusionScope
internal constructor(
    view: ComponentView
) : StaticViewInclusionScope<ComponentView>(view) {

    /**
     * @see [ComponentView.addAllContainers]
     */
    fun allContainers() {
        view.addAllContainers()
    }

    /**
     * @see [ComponentView.addAllComponents]
     */
    fun allComponents() {
        view.addAllComponents()
    }

    /**
     * @see [ComponentView.addExternalDependencies]
     */
    fun externalDependencies() {
        view.addExternalDependencies()
    }

    /**
     * @see [ComponentView.add]
     */
    fun container(
        container: Container,
        addRelationship: Boolean = true
    ) {
        view.add(container, addRelationship)
    }

    /**
     * @see [ComponentView.add]
     */
    fun component(
        component: Component,
        addRelationship: Boolean = true
    ) {
        view.add(component, addRelationship)
    }

    override fun add(element: Element, addRelationships: Boolean) = when (element) {
        is Person -> person(element, addRelationships)
        is SoftwareSystem -> softwareSystem(element, addRelationships)
        is Container -> container(element, addRelationships)
        is Component -> component(element, addRelationships)
        else -> throw IllegalArgumentException(
            "A person or software system or container or component must be specified. element: $element"
        )
    }
}

@StructurizrDslMarker
class ComponentViewExclusionScope
internal constructor(
    view: ComponentView
) : StaticViewExclusionScope<ComponentView>(view) {

    /**
     * @see [ComponentView.remove]
     */
    fun container(
        container: Container
    ) {
        view.remove(container)
    }

    /**
     * @see [ComponentView.remove]
     */
    fun component(
        component: Component
    ) {
        view.remove(component)
    }
}
