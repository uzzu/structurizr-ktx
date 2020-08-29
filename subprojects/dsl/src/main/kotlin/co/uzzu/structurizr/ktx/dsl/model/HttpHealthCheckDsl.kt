package co.uzzu.structurizr.ktx.dsl.model

import com.structurizr.model.HttpHealthCheck

/**
 * @see [HttpHealthCheck.addHeader]
 */
fun HttpHealthCheck.header(name: String, value: String) {
    addHeader(name, value)
}
