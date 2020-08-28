@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.CodeElement
import com.structurizr.model.Component

/**
 * @see [Component.addSupportingType]
 */
fun Component.SupportingType(type: String, block: ApplyBlock<CodeElement>? = null): CodeElement =
    addSupportingType(type).applyIfNotNull(block)
