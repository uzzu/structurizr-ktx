package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Person
import com.structurizr.model.Relationship
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement

fun StaticStructureElement.uses(
    destination: SoftwareSystem,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<Relationship>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)?.applyIfNotNull(block)

fun StaticStructureElement.uses(
    destination: Container,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<Relationship>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)?.applyIfNotNull(block)

fun StaticStructureElement.uses(
    destination: Component,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<Relationship>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle)?.applyIfNotNull(block)

fun StaticStructureElement.delivers(
    destination: Person,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    block: ApplyBlock<Relationship>? = null
): Relationship? =
    delivers(destination, description, technology, interactionStyle)?.applyIfNotNull(block)

fun StaticStructureElement.uses(
    destination: StaticStructureElement,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle? = null,
    tags: Array<String?>? = null,
    block: ApplyBlock<Relationship>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle, tags)?.applyIfNotNull(block)
