package co.uzzu.structurizr.ktx.dsl.model

import com.structurizr.model.HttpHealthCheck

fun HttpHealthCheck.header(name: String, value: String) {
    addHeader(name, value)
}
