@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Container
import com.structurizr.model.SoftwareSystem

/**
 * @see [SoftwareSystem.addContainer]
 */
fun SoftwareSystem.Container(
    name: String,
    description: String? = null,
    technology: String? = null,
    block: ApplyBlock<Container>? = null
): Container =
    addContainer(name, description, technology).applyIfNotNull(block)
