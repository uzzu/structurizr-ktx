package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.plantuml.C4Direction
import co.uzzu.structurizr.ktx.plantuml.C4ElementType
import co.uzzu.structurizr.ktx.plantuml.C4RelationshipMode

val SoftwareSystemScope.c4: C4ElementScope
    get() = C4ElementScope(this)

fun SoftwareSystemScope.c4(block: C4ElementScope.() -> Unit) {
    block(C4ElementScope(this))
}

val ContainerScope.c4: C4ElementScope
    get() = C4ElementScope(this)

fun ContainerScope.c4(block: C4ElementScope.() -> Unit) {
    block(C4ElementScope(this))
}

val ComponentScope.c4: C4ElementScope
    get() = C4ElementScope(this)

fun ComponentScope.c4(block: C4ElementScope.() -> Unit) {
    block(C4ElementScope(this))
}

val SoftwareSystemInstanceScope.c4: C4ElementScope
    get() = C4ElementScope(this)

fun SoftwareSystemInstanceScope.c4(block: C4ElementScope.() -> Unit) {
    block(C4ElementScope(this))
}

val ContainerInstanceScope.c4: C4ElementScope
    get() = C4ElementScope(this)

fun ContainerInstanceScope.c4(block: C4ElementScope.() -> Unit) {
    block(C4ElementScope(this))
}

val RelationshipScope.c4: C4RelationshipScope
    get() = C4RelationshipScope(this)

fun RelationshipScope.c4(block: C4RelationshipScope.() -> Unit) {
    block(C4RelationshipScope(this))
}

@StructurizrDslMarker
class C4ElementScope
internal constructor(
    private val scope: ElementScope<*>
) {
    /**
     * @see [C4ElementType]
     */
    var type: C4ElementType
        get() = throw NotImplementedError()
        set(value) {
            scope.property(C4ElementType.propertyName, value.value)
        }
}

@StructurizrDslMarker
class C4RelationshipScope(
    private val scope: RelationshipScope
) {
    /**
     * @see [C4RelationshipMode]
     */
    var mode: C4RelationshipMode
        get() = throw UnsupportedOperationException()
        set(value) {
            scope.property(C4Direction.propertyName, value.value)
        }

    /**
     * @see C4Direction
     */
    var direction: C4Direction
        get() = throw UnsupportedOperationException()
        set(value) {
            scope.property(C4Direction.propertyName, value.value)
        }
}
