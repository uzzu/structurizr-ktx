package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.HttpHealthCheck

@StructurizrDslMarker
class HttpHealthCheckScope
internal constructor(
    private val element: HttpHealthCheck
) {
    /**
     * @see [HttpHealthCheck.addHeader]
     */
    fun HttpHealthCheck.header(name: String, value: String) {
        element.addHeader(name, value)
    }
}
