@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Container
import com.structurizr.model.Location
import com.structurizr.model.SoftwareSystem
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class SoftwareSystemScope
internal constructor(
    element: SoftwareSystem
) : StaticStructureElementScope<SoftwareSystem>(element), ModelRelationshipWritable {
    /**
     * @see [SoftwareSystem.location]
     */
    var location: Location
        get() = element.location
        set(value) {
            element.location = value
        }

    /**
     * @see [SoftwareSystem.addContainer]
     */
    fun Container(
        name: String,
        description: String? = null,
        technology: String? = null,
        block: ContainerScope.() -> Unit = Any::doNothing
    ): Container {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addContainer(name, description, technology)
            .apply { block(ContainerScope(this)) }
    }
}
