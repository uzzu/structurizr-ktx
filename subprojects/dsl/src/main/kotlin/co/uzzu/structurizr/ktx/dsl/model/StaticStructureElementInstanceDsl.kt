package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.HttpHealthCheck
import com.structurizr.model.StaticStructureElementInstance

fun StaticStructureElementInstance.HealthCheck(
    name: String,
    url: String,
    block: ApplyBlock<HttpHealthCheck>? = null
): HttpHealthCheck =
    addHealthCheck(name, url).applyIfNotNull(block)

fun StaticStructureElementInstance.HealthCheck(
    name: String,
    url: String,
    interval: Int,
    timeout: Long,
    block: ApplyBlock<HttpHealthCheck>? = null
): HttpHealthCheck =
    addHealthCheck(name, url, interval, timeout).applyIfNotNull(block)
