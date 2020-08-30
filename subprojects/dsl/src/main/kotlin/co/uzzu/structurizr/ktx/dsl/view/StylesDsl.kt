@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.view.ElementStyle
import com.structurizr.view.RelationshipStyle
import com.structurizr.view.Styles

class StylesScope(
    private val styles: Styles
) {
    /**
     * @see [Styles.addElementStyle]
     */
    fun element(
        tag: String,
        block: ApplyBlock<ElementStyle>? = null
    ): ElementStyle =
        styles.addElementStyle(tag).applyIfNotNull(block)

    /**
     * @see [Styles.addRelationshipStyle]
     */
    fun relationship(
        tag: String,
        block: ApplyBlock<RelationshipStyle>? = null
    ): RelationshipStyle =
        styles.addRelationshipStyle(tag).applyIfNotNull(block)

    /**
     * @see [Styles.addDefaultStyles]
     */
    fun default() {
        styles.addDefaultStyles()
    }
}
