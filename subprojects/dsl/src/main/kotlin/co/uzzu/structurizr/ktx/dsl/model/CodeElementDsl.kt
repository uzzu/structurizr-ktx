package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.CodeElement

@StructurizrDslMarker
class CodeElementScope
internal constructor(
    private val element: CodeElement
) {

    /**
     * @see [CodeElement.description]
     */
    var description: String?
        get() = element.description
        set(value) {
            element.description = value
        }

    /**
     * @see [CodeElement.language]
     */
    var language: String?
        get() = element.language
        set(value) {
            element.language = value
        }

    /**
     * @see [CodeElement.category]
     */
    var category: String?
        get() = element.category
        set(value) {
            element.category = value
        }

    /**
     * @see [CodeElement.visibility]
     */
    var visibility: String?
        get() = element.visibility
        set(value) {
            element.visibility = value
        }

    /**
     * @see [CodeElement.size]
     */
    var size: Long
        get() = element.size
        set(value) {
            element.size = value
        }
}
