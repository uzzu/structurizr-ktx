@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.CodeElement
import com.structurizr.model.Component
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ComponentScope
internal constructor(
    element: Component
) : StaticStructureElementScope<Component>(element) {

    /**
     * @see [Component.technology]
     */
    var technology: String?
        get() = element.technology
        set(value) {
            element.technology = value
        }

    /**
     * @see [Component.setType]
     */
    fun Code(
        type: String,
        block: CodeElementScope.() -> Unit = Any::doNothing
    ): CodeElement {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.setType(type)
            .apply { block(CodeElementScope(this)) }
    }

    /**
     * @see [Component.addSupportingType]
     */
    fun SupportingType(
        type: String,
        block: CodeElementScope.() -> Unit = Any::doNothing
    ): CodeElement {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addSupportingType(type)
            .apply { block(CodeElementScope(this)) }
    }
}
