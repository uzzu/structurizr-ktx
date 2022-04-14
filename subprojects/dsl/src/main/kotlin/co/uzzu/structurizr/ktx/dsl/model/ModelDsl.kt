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
    @JvmName("relWithPerson")
    fun rel(
        pair: Pair<Person, Person>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, block)
    }

    /**
     * @see [Person.interactsWith]
     */
    @JvmName("relationshipWithPerson")
    fun relationship(
        pair: Pair<Person, Person>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.interactsWith(destination, description, technology, interactionStyle)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relToSoftwareSystem")
    fun rel(
        pair: Pair<StaticStructureElement, SoftwareSystem>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relationshipToSoftwareSystem")
    fun relationship(
        pair: Pair<StaticStructureElement, SoftwareSystem>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description, technology, interactionStyle)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relToContainer")
    fun rel(
        pair: Pair<StaticStructureElement, Container>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relationshipToContainer")
    fun relationship(
        pair: Pair<StaticStructureElement, Container>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description, technology, interactionStyle)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relToComponent")
    fun rel(
        pair: Pair<StaticStructureElement, Component>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relationshipToComponent")
    fun relationship(
        pair: Pair<StaticStructureElement, Component>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description, technology, interactionStyle)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    /**
     * @see [StaticStructureElement.delivers]
     * @param block [RelationshipScope]
     */
    @JvmName("relToPerson")
    fun rel(
        pair: Pair<StaticStructureElement, Person>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, block)
    }

    /**
     * @see [StaticStructureElement.delivers]
     * @param block [RelationshipScope]
     */
    @JvmName("relationshipToPerson")
    fun relationship(
        pair: Pair<StaticStructureElement, Person>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle? = null,
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.delivers(destination, description, technology, interactionStyle)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    /**
     * @see [InfrastructureNode.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relWithInfrastructureNode")
    fun rel(
        pair: Pair<InfrastructureNode, DeploymentElement>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        tags: Array<String> = emptyArray(),
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, technology, interactionStyle, tags, block)
    }

    /**
     * @see [InfrastructureNode.uses]
     * @param block [RelationshipScope]
     */
    @JvmName("relationshipWithInfrastructureNode")
    fun relationship(
        pair: Pair<InfrastructureNode, DeploymentElement>,
        description: String? = null,
        technology: String? = null,
        interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
        tags: Array<String> = emptyArray(),
        block: RelationshipScope.() -> Unit = Any::doNothing
    ): Relationship? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description, technology, interactionStyle, tags)
                ?.apply { block(RelationshipScope(this)) }
        }
    }

    // endregion
}
