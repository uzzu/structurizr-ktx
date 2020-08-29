package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.InteractionStyle
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
    block: ApplyBlock<Relationship>? = null
): Relationship =
    interactsWith(destination, description, technology, interactionStyle).applyIfNotNull(block)
