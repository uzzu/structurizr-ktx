package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Element
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.RelationshipView
import com.structurizr.view.StaticView

// region ViewScope

/**
 * @see [StaticView.addAnimation]
 */
fun <TView : StaticView> ViewScope<TView>.animations(
    block: ApplyBlock<StaticViewAnimationScope>
) {
    StaticViewAnimationScope(view).apply(block)
}

class StaticViewAnimationScope(
    private val view: StaticView
) {
    /**
     * @see [StaticView.addAnimation]
     */
    fun step(vararg elements: Element) {
        view.addAnimation(*elements)
    }
}

// endregion

// region InclusionScope

/**
 * @see [StaticView.addAllElements]
 */
fun <TView : StaticView> InclusionScope<TView>.allElements() {
    view.addAllElements()
}

/**
 * @see [StaticView.addAllSoftwareSystems]
 */
fun <TView : StaticView> InclusionScope<TView>.allSoftwareSystems() {
    view.addAllSoftwareSystems()
}

/**
 * @see [StaticView.addAllPeople]
 */
fun <TView : StaticView> InclusionScope<TView>.allPeople() {
    view.addAllPeople()
}

/**
 * @see [StaticView.addDefaultElements]
 */
fun <TView : StaticView> InclusionScope<TView>.defaultElements() {
    view.addDefaultElements()
}

/**
 * @see [StaticView.add]
 */
fun <TView : StaticView> InclusionScope<TView>.softwareSystem(
    softwareSystem: SoftwareSystem,
    addRelationship: Boolean = true
) {
    view.add(softwareSystem, addRelationship)
}

/**
 * @see [StaticView.add]
 */
fun <TView : StaticView> InclusionScope<TView>.person(
    person: Person,
    addRelationship: Boolean = true
) {
    view.add(person, addRelationship)
}

/**
 * @see [StaticView.add]
 */
fun <TView : StaticView> InclusionScope<TView>.relationship(
    relationship: Relationship,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    view.add(relationship)
        .applyIfNotNull(block)

/**
 * @see [StaticView.addNearestNeighbours]
 */
fun <TView : StaticView> InclusionScope<TView>.nearestNeighbours(
    element: Element
) {
    view.addNearestNeighbours(element)
}

// endregion

// region ExclusionScope

/**
 * @see [StaticView.remove]
 */
fun <TView : StaticView> ExclusionScope<TView>.softwareSystem(softwareSystem: SoftwareSystem) {
    view.remove(softwareSystem)
}

/**
 * @see [StaticView.remove]
 */
fun <TView : StaticView> ExclusionScope<TView>.person(person: Person) {
    view.remove(person)
}

/**
 * @see [StaticView.removeElementsThatAreUnreachableFrom]
 */
fun <TView : StaticView> ExclusionScope<TView>.elementsUnreachableFrom(element: Element) {
    view.removeElementsThatAreUnreachableFrom(element)
}

/**
 * @see [StaticView.removeElementsWithTag]
 */
fun <TView : StaticView> ExclusionScope<TView>.elementsWithTag(tag: String) {
    view.removeElementsWithTag(tag)
}

/**
 * @see [StaticView.removeRelationshipsWithTag]
 */
fun <TView : StaticView> ExclusionScope<TView>.relationshipWithTag(tag: String) {
    view.removeRelationshipsWithTag(tag)
}

// endregion
