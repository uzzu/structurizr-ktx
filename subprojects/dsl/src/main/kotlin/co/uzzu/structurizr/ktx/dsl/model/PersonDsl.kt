package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Location
import com.structurizr.model.Person
import com.structurizr.model.Relationship

/**
 * @see [Person.interactsWith]
 */
fun Person.interactsWith(
    destination: Person,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    interactsWith(destination, description, technology, interactionStyle)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

var ElementScope<Person>.location: Location
    get() = element.location
    set(value) {
        element.location = value
    }

/**
 * @see [Person.interactsWith]
 */
fun ElementScope<Person>.interactsWith(
    destination: Person,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    element.interactsWith(destination, description, technology, interactionStyle, block)
