@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Component
import com.structurizr.model.Container
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KClass

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ContainerScope
internal constructor(
    element: Container
) : StaticStructureElementScope<Container>(element) {

    /**
     * @see [Container.technology]
     */
    var technology: String?
        get() = element.technology
        set(value) {
            element.technology = value
        }

    /**
     * @see [Container.addComponent]
     */
    fun Component(
        name: String,
        description: String? = null,
        technology: String? = null,
        block: ComponentScope.() -> Unit = Any::doNothing
    ): Component {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addComponent(name, description, technology)
            .apply { block(ComponentScope(this)) }
    }

    /**
     * @see [Container.addComponent]
     */
    fun <T : Any> Component(
        name: String,
        klass: KClass<T>,
        description: String? = null,
        technology: String? = null,
        block: ComponentScope.() -> Unit = Any::doNothing
    ): Component {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addComponent(name, klass.java, description, technology)
            .apply { block(ComponentScope(this)) }
    }
}
