package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.DeploymentElement
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Relationship

fun InfrastructureNode.uses(
    destination: DeploymentElement,
    description: String?,
    technology: String?,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    uses(destination, description, technology).applyIfNotNull(block)

fun InfrastructureNode.uses(
    destination: DeploymentElement,
    description: String?,
    technology: String?,
    interactionStyle: InteractionStyle,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    uses(destination, description, technology, interactionStyle).applyIfNotNull(block)

fun InfrastructureNode.uses(
    destination: DeploymentElement,
    description: String?,
    technology: String?,
    interactionStyle: InteractionStyle,
    tags: List<String>,
    block: ApplyBlock<Relationship>? = null
): Relationship =
    uses(destination, description, technology, interactionStyle, tags).applyIfNotNull(block)
