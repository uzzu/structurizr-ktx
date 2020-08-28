package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Person
import com.structurizr.model.Relationship

fun Person.interactsWith(
    destination: Person,
    description: String,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    interactsWith(destination, description).applyIfNotNull(block)

fun Person.interactsWith(
    destination: Person,
    description: String?,
    technology: String?,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    interactsWith(destination, description, technology).applyIfNotNull(block)

fun Person.interactsWith(
    destination: Person,
    description: String?,
    technology: String?,
    interactionStyle: InteractionStyle,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    interactsWith(destination, description, technology, interactionStyle).applyIfNotNull(block)
