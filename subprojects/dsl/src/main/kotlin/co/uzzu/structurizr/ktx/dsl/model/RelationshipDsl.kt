@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Perspective
import com.structurizr.model.Relationship
import com.structurizr.model.descriptionPublic
import com.structurizr.model.interactionStylePublic
import com.structurizr.model.technologyPublic

@StructurizrDslMarker
class RelationshipScope
internal constructor(
    private val relationship: Relationship
) {
    /**
     * @see [Relationship.description]
     */
    var description: String?
        get() = relationship.descriptionPublic
        set(value) {
            relationship.descriptionPublic = value
        }

    /**
     * @see [Relationship.technology]
     */
    var technology: String?
        get() = relationship.technologyPublic
        set(value) {
            relationship.technologyPublic = value
        }

    /**
     * @see [Relationship.interactionStyle]
     */
    var interactionStyle: InteractionStyle?
        get() = relationship.interactionStyle
        set(value) {
            relationship.interactionStylePublic = value
        }

    /**
     * @see [Relationship.addTags]
     */
    fun tags(vararg tags: String) {
        relationship.addTags(*tags)
    }

    /**
     * @see [Relationship.addProperty]
     */
    fun property(name: String, value: String) {
        relationship.addProperty(name, value)
    }

    /**
     * @see [Relationship.addPerspective]
     */
    fun Perspective(
        name: String,
        description: String
    ): Perspective =
        relationship.addPerspective(name, description)
}
