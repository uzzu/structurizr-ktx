@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.view.ElementStyle
import com.structurizr.view.RelationshipStyle
import com.structurizr.view.Styles

@StructurizrDslMarker
class StylesScope
internal constructor(
    private val styles: Styles
) {
    /**
     * @see [Styles.addElementStyle]
     */
    fun element(
        tag: String,
        block: ElementStyle.() -> Unit
    ): ElementStyle =
        styles.addElementStyle(tag).apply(block)

    /**
     * @see [Styles.addRelationshipStyle]
     */
    fun relationship(
        tag: String,
        block: RelationshipStyle.() -> Unit
    ): RelationshipStyle =
        styles.addRelationshipStyle(tag).apply(block)

    /**
     * @see [Styles.addDefaultStyles]
     */
    fun default() {
        styles.addDefaultStyles()
    }
}
