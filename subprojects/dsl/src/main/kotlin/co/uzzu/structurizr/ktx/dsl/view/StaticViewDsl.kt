package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
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
        block: RelationshipView.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return view.add(relationship).apply(block)
    }

    /**
     * @see [StaticView.addNearestNeighbours]
     */
    fun nearestNeighbours(element: Element) {
        view.addNearestNeighbours(element)
    }
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
