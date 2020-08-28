package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Perspective
import com.structurizr.model.Relationship

/**
 * @see [Relationship.addTags]
 */
fun Relationship.tags(vararg tags: String) {
    addTags(*tags)
}

/**
 * @see [Relationship.addProperty]
 */
fun Relationship.property(name: String, value: String) {
    addProperty(name, value)
}

/**
 * @see [Relationship.addPerspective]
 */
fun Relationship.Perspective(
    name: String,
    description: String,
    block: ApplyBlock<Perspective>? = null
): Perspective =
    addPerspective(name, description).applyIfNotNull(block)
