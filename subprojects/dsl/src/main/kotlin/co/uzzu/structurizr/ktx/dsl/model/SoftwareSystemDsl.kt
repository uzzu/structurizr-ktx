@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.DeploymentElement
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Location
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class SoftwareSystemScope
internal constructor(
    element: SoftwareSystem
) : StaticStructureElementScope<SoftwareSystem>(element) {
    /**
     * @see [SoftwareSystem.location]
     */
    var location: Location
        get() = element.location
        set(value) {
            element.location = value
        }

    /**
     * @see [SoftwareSystem.addContainer]
     */
    fun Container(
        name: String,
        description: String? = null,
        technology: String? = null,
        block: ContainerScope.() -> Unit = Any::doNothing
    ): Container {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addContainer(name, description, technology)
            .apply { block(ContainerScope(this)) }
    }

    // region relationship dsl

    /**
     * @see [Person.interactsWith]
     */
    fun Person.interactsWith(
        destination: Person,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        interactsWith(destination, description, technology, interactionStyle)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    fun StaticStructureElement.uses(
        destination: SoftwareSystem,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        uses(destination, description, technology, interactionStyle)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    fun StaticStructureElement.uses(
        destination: Container,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        uses(destination, description, technology, interactionStyle)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    fun StaticStructureElement.uses(
        destination: Component,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        uses(destination, description, technology, interactionStyle)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [StaticStructureElement.delivers]
     * @param block [RelationshipScope]
     */
    fun StaticStructureElement.delivers(
        destination: Person,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        delivers(destination, description, technology, interactionStyle)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    fun StaticStructureElement.uses(
        destination: StaticStructureElement,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        tags: Array<String?>? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        uses(destination, description, technology, interactionStyle, tags)
            ?.apply { block(RelationshipScope(this)) }

    /**
     * @see [InfrastructureNode.uses]
     */
    fun InfrastructureNode.uses(
        destination: DeploymentElement,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        tags: Array<String> = emptyArray(),
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? =
        uses(destination, description, technology, interactionStyle, tags)
            ?.apply { block(RelationshipScope(this)) }

    // endregion
}
