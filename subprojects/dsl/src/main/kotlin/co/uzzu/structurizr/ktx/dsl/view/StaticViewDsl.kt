package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import co.uzzu.structurizr.ktx.dsl.model.RelationshipScope
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.DeploymentElement
import com.structurizr.model.Element
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement
import com.structurizr.view.RelationshipView
import com.structurizr.view.StaticView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
abstract class StaticViewScope
<TView : StaticView, TInclusionScope : StaticViewInclusionScope<TView>, TExclusionScope : StaticViewExclusionScope<TView>>
internal constructor(
    view: TView,
    inclusionScope: TInclusionScope,
    exclusionScope: TExclusionScope
) : ViewScope<TView, TInclusionScope, TExclusionScope>(view, inclusionScope, exclusionScope) {

    /**
     * @see [StaticView.addAnimation]
     */
    fun animations(block: StaticViewAnimationScope.() -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        StaticViewAnimationScope(view).apply(block)
    }
}

@OptIn(ExperimentalContracts::class)
abstract class StaticViewInclusionScope<TView : StaticView>
internal constructor(
    view: TView
) : InclusionScope<TView>(view) {

    /**
     * @see [StaticView.addAllElements]
     */
    fun allElements() {
        view.addAllElements()
    }

    /**
     * @see [StaticView.addAllSoftwareSystems]
     */
    fun allSoftwareSystems() {
        view.addAllSoftwareSystems()
    }

    /**
     * @see [StaticView.addAllPeople]
     */
    fun allPeople() {
        view.addAllPeople()
    }

    /**
     * @see [StaticView.addDefaultElements]
     */
    fun defaultElements() {
        view.addDefaultElements()
    }

    /**
     * @see [StaticView.add]
     */
    fun softwareSystem(
        softwareSystem: SoftwareSystem,
        addRelationship: Boolean = true
    ) {
        view.add(softwareSystem, addRelationship)
    }

    /**
     * @see [StaticView.add]
     */
    fun person(
        person: Person,
        addRelationship: Boolean = true
    ) {
        view.add(person, addRelationship)
    }

    /**
     * @see [StaticView.add]
     */
    fun relationship(
        relationship: Relationship,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        add(relationship.source, false)
        add(relationship.destination, false)
        return view.add(relationship)?.apply { block(RelationshipViewScope(this)) }
    }

    /**
     * @see [StaticView.addNearestNeighbours]
     */
    fun nearestNeighbours(element: Element) {
        view.addNearestNeighbours(element)
    }

    protected abstract fun add(element: Element, addRelationships: Boolean)

    // region relationship dsl

    /**
     * @see [Person.interactsWith]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relWithPerson")
    fun rel(
        pair: Pair<Person, Person>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [Person.interactsWith]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipWithPerson")
    fun relationship(
        pair: Pair<Person, Person>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.interactsWith(destination, description)
                ?.let { relationship(it, block) }
        }
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relToSoftwareSystem")
    fun rel(
        pair: Pair<StaticStructureElement, SoftwareSystem>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipToSoftwareSystem")
    fun relationship(
        pair: Pair<StaticStructureElement, SoftwareSystem>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description)
                ?.let { relationship(it, block) }
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
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipToContainer")
    fun relationship(
        pair: Pair<StaticStructureElement, Container>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description)
                ?.let { relationship(it, block) }
        }
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relToComponent")
    fun rel(
        pair: Pair<StaticStructureElement, Component>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [StaticStructureElement.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipToComponent")
    fun relationship(
        pair: Pair<StaticStructureElement, Component>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description)
                ?.let { relationship(it, block) }
        }
    }

    /**
     * @see [StaticStructureElement.delivers]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relToPerson")
    fun rel(
        pair: Pair<StaticStructureElement, Person>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [StaticStructureElement.delivers]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipToPerson")
    fun relationship(
        pair: Pair<StaticStructureElement, Person>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.delivers(destination, description)
                ?.let { relationship(it, block) }
        }
    }

    /**
     * @see [InfrastructureNode.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relWithInfrastructureNode")
    fun rel(
        pair: Pair<InfrastructureNode, DeploymentElement>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [InfrastructureNode.uses]
     * @param block [RelationshipViewScope]
     */
    @JvmName("relationshipWithInfrastructureNode")
    fun relationship(
        pair: Pair<InfrastructureNode, DeploymentElement>,
        description: String? = null,
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }

        return pair.let { (source, destination) ->
            source.uses(destination, description, null, InteractionStyle.Synchronous, emptyArray())
                ?.let { relationship(it, block) }
        }
    }

    // endregion
}

abstract class StaticViewExclusionScope<TView : StaticView>
internal constructor(
    view: TView
) : ExclusionScope<TView>(view) {

    /**
     * @see [StaticView.remove]
     */
    fun softwareSystem(softwareSystem: SoftwareSystem) {
        view.remove(softwareSystem)
    }

    /**
     * @see [StaticView.remove]
     */
    fun person(person: Person) {
        view.remove(person)
    }

    /**
     * @see [StaticView.removeElementsThatAreUnreachableFrom]
     */
    fun elementsUnreachableFrom(element: Element) {
        view.removeElementsThatAreUnreachableFrom(element)
    }

    /**
     * @see [StaticView.removeElementsWithTag]
     */
    fun elementsWithTag(tag: String) {
        view.removeElementsWithTag(tag)
    }

    /**
     * @see [StaticView.removeRelationshipsWithTag]
     */
    fun relationshipWithTag(tag: String) {
        view.removeRelationshipsWithTag(tag)
    }
}

@StructurizrDslMarker
class StaticViewAnimationScope
internal constructor(
    private val view: StaticView
) {
    /**
     * @see [StaticView.addAnimation]
     */
    fun step(vararg elements: Element) {
        view.addAnimation(*elements)
    }
}
