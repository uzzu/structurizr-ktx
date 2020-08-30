@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.CodeElement
import com.structurizr.model.Component

/**
 * @see [Component.technology]
 */
var ElementScope<Component>.technology: String?
    get() = element.technology
    set(value) {
        element.technology = value
    }

/**
 * @see [Component.setType]
 */
fun ElementScope<Component>.Code(
    type: String,
    block: ApplyBlock<CodeElement>
): CodeElement =
    element.setType(type).applyIfNotNull(block)

/**
 * @see [Component.addSupportingType]
 */
fun ElementScope<Component>.SupportingType(
    type: String,
    block: ApplyBlock<CodeElement>? = null
): CodeElement =
    element.addSupportingType(type).applyIfNotNull(block)
