package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.model.RelationshipScope
import com.structurizr.view.RelationshipView
import com.structurizr.view.Routing
import com.structurizr.view.Vertex

/**
 * @see [RelationshipView]
 */
@StructurizrDslMarker
class RelationshipViewScope
internal constructor(
    private val view: RelationshipView
) {

    /**
     * @see [RelationshipView.description]
     */
    var description: String
        get() = view.description
        set(value) {
            view.description = value
        }

    /**
     * @see [RelationshipView.order]
     */
    var order: String
        get() = view.order
        set(value) {
            view.order = value
        }

    /**
     * @see [RelationshipView.vertices]
     */
    var vertices: Collection<Vertex>
        get() = view.vertices
        set(value) {
            view.vertices = value
        }

    /**
     * @see [RelationshipView.routing]
     */
    var routing: Routing
        get() = view.routing
        set(value) {
            view.routing = value
        }

    /**
     * @see [RelationshipView.position]
     */
    var position: Int
        get() = view.position
        set(value) {
            view.position = value
        }

    /**
     * @see RelationshipScope
     * @param block [RelationshipScope]
     */
    fun element(block: RelationshipScope.() -> Unit) {
        block(RelationshipScope(view.relationship))
    }
}
