@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Component
import com.structurizr.model.Container
import kotlin.reflect.KClass

/**
 * @see [Container.technology]
 */
var ElementScope<Container>.technology: String?
    get() = element.technology
    set(value) {
        element.technology = value
    }

/**
 * @see [Container.addComponent]
 */
fun ElementScope<Container>.Component(
    name: String,
    description: String? = null,
    technology: String? = null,
    block: ApplyBlock<ElementScope<Component>>? = null
): Component =
    element.addComponent(name, description, technology)
        .apply { block?.let { ElementScope(this).apply(it) } }

/**
 * @see [Container.addComponent]
 */
fun <T : Any> ElementScope<Container>.Component(
    name: String,
    klass: KClass<T>,
    description: String? = null,
    technology: String? = null,
    block: ApplyBlock<ElementScope<Component>>? = null
): Component =
    element.addComponent(name, klass.java, description, technology)
        .apply { block?.let { ElementScope(this).apply(block) } }
