package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.InfrastructureNode

@StructurizrDslMarker
class InfrastructureNodeScope
internal constructor(
    element: InfrastructureNode
) : ElementScope<InfrastructureNode>(element) {

    /**
     * @see [InfrastructureNode.technology]
     */
    var technology: String?
        get() = element.technology
        set(value) {
            element.technology = value
        }
}
