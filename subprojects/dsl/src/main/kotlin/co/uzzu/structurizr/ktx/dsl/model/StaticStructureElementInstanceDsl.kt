package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.HttpHealthCheck
import com.structurizr.model.StaticStructureElementInstance
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
abstract class StaticStructureElementInstanceScope<TElement : StaticStructureElementInstance>
internal constructor(
    element: TElement
) : ElementScope<TElement>(element) {

    /**
     * @see [StaticStructureElementInstance.addHealthCheck]
     */
    fun HealthCheck(
        name: String,
        url: String,
        interval: Int = 60,
        timeout: Long = 0,
        block: HttpHealthCheckScope.() -> Unit = Any::doNothing
    ): HttpHealthCheck {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addHealthCheck(name, url, interval, timeout)
            .apply { block(HttpHealthCheckScope(this)) }
    }
}
