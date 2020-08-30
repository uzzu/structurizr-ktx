@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Element
import com.structurizr.model.Perspective

class ElementScope<TElement : Element>(internal val element: TElement)

/**
 * @see [Element.description]
 */
var <TElement : Element> ElementScope<TElement>.description: String?
    get() = element.description
    set(value) {
        element.description = value
    }

/**
 * @see [Element.addTags]
 */
fun <TElement : Element> ElementScope<TElement>.tags(vararg tags: String) {
    element.addTags(*tags)
}

/**
 * @see [Element.addProperty]
 */
fun <TElement : Element> ElementScope<TElement>.property(name: String, value: String) {
    element.addProperty(name, value)
}

/**
 * @see [Element.addPerspective]
 */
fun <TElement : Element> ElementScope<TElement>.Perspective(
    name: String,
    description: String,
    block: ApplyBlock<Perspective>? = null
): Perspective =
    element.addPerspective(name, description).applyIfNotNull(block)
