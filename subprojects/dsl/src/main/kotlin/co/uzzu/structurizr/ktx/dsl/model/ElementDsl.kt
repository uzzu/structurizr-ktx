@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Element
import com.structurizr.model.Perspective

/**
 * @see [Element.addTags]
 */
fun Element.tags(vararg tags: String) {
    addTags(*tags)
}

/**
 * @see [Element.addProperty]
 */
fun Element.property(name: String, value: String) {
    addProperty(name, value)
}

/**
 * @see [Element.addPerspective]
 */
fun Element.Perspective(
    name: String,
    description: String,
    block: ApplyBlock<Perspective>? = null
): Perspective =
    addPerspective(name, description).applyIfNotNull(block)
