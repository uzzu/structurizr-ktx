package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.HttpHealthCheck
import com.structurizr.model.StaticStructureElementInstance

/**
 * @see [StaticStructureElementInstance.addHealthCheck]
 */
fun <TElement : StaticStructureElementInstance> ElementScope<TElement>.HealthCheck(
    name: String,
    url: String,
    interval: Int = 60,
    timeout: Long = 0,
    block: ApplyBlock<HttpHealthCheck>? = null
): HttpHealthCheck =
    element.addHealthCheck(name, url, interval, timeout).applyIfNotNull(block)
