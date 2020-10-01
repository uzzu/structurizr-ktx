@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import com.structurizr.model.Element
import com.structurizr.model.Perspective

abstract class ElementScope<TElement : Element>
internal constructor(
    protected val element: TElement
) {

    /**
     * @see [Element.description]
     */
    var description: String?
        get() = element.description
        set(value) {
            element.description = value
        }

    /**
     * @see [Element.addTags]
     */
    fun tags(vararg tags: String) {
        element.addTags(*tags)
    }

    /**
     * @see [Element.addProperty]
     */
    fun property(name: String, value: String) {
        element.addProperty(name, value)
    }

    /**
     * @see [Element.addPerspective]
     */
    fun Perspective(
        name: String,
        description: String
    ): Perspective =
        element.addPerspective(name, description)
}
