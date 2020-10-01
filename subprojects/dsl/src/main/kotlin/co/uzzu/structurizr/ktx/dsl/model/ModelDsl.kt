@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.DeploymentElement
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ModelScope
internal constructor(
    private val model: Model
) {
    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        name: String,
        description: String? = null,
        block: SoftwareSystemScope.() -> Unit = Any::doNothing
    ): SoftwareSystem {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addSoftwareSystem(name, description)
            .apply { block(SoftwareSystemScope(this)) }
    }

    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        location: Location,
        name: String,
        description: String? = null,
        block: SoftwareSystemScope.() -> Unit = Any::doNothing
    ): SoftwareSystem {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addSoftwareSystem(location, name, description)
            .apply { block(SoftwareSystemScope(this)) }
    }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        name: String,
        description: String? = null,
        block: PersonScope.() -> Unit = Any::doNothing
    ): Person {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addPerson(name, description)
            .apply { block(PersonScope(this)) }
    }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        location: Location,
        name: String,
        description: String? = null,
        block: PersonScope.() -> Unit = Any::doNothing
    ): Person {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addPerson(location, name, description)
            .apply { block(PersonScope(this)) }
    }

    /**
     * @see [Model.addDeploymentNode]
     */
    fun DeploymentNode(
        name: String,
        description: String? = null,
        technology: String? = null,
        instances: Int = 1,
        properties: Map<String, String>? = null,
        block: DeploymentNodeScope.() -> Unit = Any::doNothing
    ): DeploymentNode {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addDeploymentNode(name, description, technology, instances, properties)
            .apply { block(DeploymentNodeScope(this)) }
    }

    /**
     * @see [Model.addDeploymentNode]
     */
    fun DeploymentNode(
        environment: String?,
        name: String,
        description: String? = null,
        technology: String? = null,
        instances: Int = 1,
        properties: Map<String, String>? = null,
        block: DeploymentNodeScope.() -> Unit = Any::doNothing
    ): DeploymentNode {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addDeploymentNode(environment, name, description, technology, instances, properties)
            .apply { block(DeploymentNodeScope(this)) }
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
