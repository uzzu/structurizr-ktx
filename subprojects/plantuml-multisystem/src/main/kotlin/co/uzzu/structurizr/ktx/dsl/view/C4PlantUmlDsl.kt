package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.plantuml.C4Direction
import co.uzzu.structurizr.ktx.plantuml.C4RelationshipMode
import com.structurizr.view.RelationshipView

val RelationshipView.c4: C4RelationshipViewScope
    get() = C4RelationshipViewScope(this)

fun RelationshipView.c4(block: C4RelationshipViewScope.() -> Unit) {
    block(C4RelationshipViewScope(this))
}

@StructurizrDslMarker
class C4RelationshipViewScope(
    private val view: RelationshipView
) {
    /**
     * @see [C4RelationshipMode]
     */
    var mode: C4RelationshipMode
        get() = throw UnsupportedOperationException()
        set(value) {
            view.relationship.addProperty(C4Direction.propertyName, value.value)
        }

    /**
     * @see [C4Direction]
     */
    var direction: C4Direction
        get() = throw UnsupportedOperationException()
        set(value) {
            view.relationship.addProperty(C4Direction.propertyName, value.value)
        }
}
