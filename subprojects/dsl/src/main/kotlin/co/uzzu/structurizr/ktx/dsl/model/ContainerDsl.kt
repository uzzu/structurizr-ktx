@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Component
import com.structurizr.model.Container
import kotlin.reflect.KClass

/**
 * @see [Container.addComponent]
 */
fun Container.Component(
    name: String,
    description: String?,
    block: ApplyBlock<Component>? = null
): Component =
    addComponent(name, description).applyIfNotNull(block)

/**
 * @see [Container.addComponent]
 */
fun Container.Component(
    name: String,
    description: String?,
    technology: String?,
    block: ApplyBlock<Component>? = null
): Component =
    addComponent(name, description, technology).applyIfNotNull(block)

/**
 * @see [Container.addComponent]
 */
fun <T : Any> Container.Component(
    name: String,
    klass: KClass<T>,
    description: String?,
    technology: String?,
    block: ApplyBlock<Component>? = null
): Component =
    addComponent(name, klass.java, description, technology).applyIfNotNull(block)

/**
 * @see [Container.addComponent]
 */
fun Container.Component(
    name: String,
    type: String,
    description: String?,
    technology: String?,
    block: ApplyBlock<Component>?
): Component =
    addComponent(name, type, description, technology).applyIfNotNull(block)
