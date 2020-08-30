package com.structurizr.model

/**
 * @see [Relationship.description]
 */
var Relationship.descriptionPublic: String?
    get() = description
    set(value) {
        description = value
    }

/**
 * @see [Relationship.technology]
 */
var Relationship.technologyPublic: String?
    get() = technology
    set(value) {
        technology = value
    }

/**
 * @see [Relationship.interactionStyle]
 */
var Relationship.interactionStylePublic: InteractionStyle?
    get() = interactionStyle
    set(value) {
        interactionStyle = value
    }
