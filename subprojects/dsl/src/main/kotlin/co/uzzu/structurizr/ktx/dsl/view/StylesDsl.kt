@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.view.ElementStyle
import com.structurizr.view.RelationshipStyle
import com.structurizr.view.Styles

fun Styles.Element(
    tag: String,
    block: ApplyBlock<ElementStyle>? = null
): ElementStyle =
    addElementStyle(tag).applyIfNotNull(block)

fun Styles.Relationship(
    tag: String,
    block: ApplyBlock<RelationshipStyle>? = null
): RelationshipStyle =
    addRelationshipStyle(tag).applyIfNotNull(block)

fun Styles.hoge() {
}
