package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun StaticStructureElement.uses(
    destination: SoftwareSystem,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun StaticStructureElement.uses(
    destination: Container,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun StaticStructureElement.uses(
    destination: Component,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

/**
 * @see [StaticStructureElement.delivers]
 * @param block [RelationshipScope]
 */
fun StaticStructureElement.delivers(
    destination: Person,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    delivers(destination, description, technology, interactionStyle)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

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
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle, tags)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun <TElement : StaticStructureElement> ElementScope<TElement>.uses(
    destination: SoftwareSystem,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.uses(destination, description, technology, interactionStyle, block)

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun <TElement : StaticStructureElement> ElementScope<TElement>.uses(
    destination: Container,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.uses(destination, description, technology, interactionStyle, block)

/**
 * @see [StaticStructureElement.uses]
 * @param block [RelationshipScope]
 */
fun <TElement : StaticStructureElement> ElementScope<TElement>.uses(
    destination: Component,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.uses(destination, description, technology, interactionStyle, block)

/**
 * @see [StaticStructureElement.delivers]
 * @param block [RelationshipScope]
 */
fun <TElement : StaticStructureElement> ElementScope<TElement>.delivers(
    destination: Person,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    tags: Array<String?>? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.uses(destination, description, technology, interactionStyle, tags, block)

/**
 * @see [StaticStructureElement]
 * @param block [RelationshipScope]
 */
fun <TElement : StaticStructureElement> ElementScope<TElement>.uses(
    destination: StaticStructureElement,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    tags: Array<String?>? = null,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.uses(destination, description, technology, interactionStyle, tags, block)
