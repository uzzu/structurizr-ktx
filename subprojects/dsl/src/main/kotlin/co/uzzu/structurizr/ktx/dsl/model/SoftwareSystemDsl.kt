@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Container
import com.structurizr.model.Location
import com.structurizr.model.SoftwareSystem

/**
 * @see [SoftwareSystem.location]
 */
var ElementScope<SoftwareSystem>.location: Location
    get() = element.location
    set(value) {
        element.location = value
    }

/**
 * @see [SoftwareSystem.addContainer]
 */
fun ElementScope<SoftwareSystem>.Container(
    name: String,
    description: String? = null,
    technology: String? = null,
    block: ApplyBlock<ElementScope<Container>>? = null
): Container =
    element.addContainer(name, description, technology)
        .apply { block?.let { ElementScope(this).apply(it) } }
