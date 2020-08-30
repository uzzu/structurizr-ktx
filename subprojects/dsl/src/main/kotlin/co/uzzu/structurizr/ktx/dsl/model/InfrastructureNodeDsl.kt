package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.DeploymentElement
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Relationship

/**
 * @see [InfrastructureNode.uses]
 */
fun InfrastructureNode.uses(
    destination: DeploymentElement,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
    tags: Array<String> = emptyArray(),
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle, tags)
        ?.apply { block?.let { RelationshipScope(this).apply(it) } }

/**
 * @see [InfrastructureNode.technology]
 */
var ElementScope<InfrastructureNode>.technology: String?
    get() = element.technology
    set(value) {
        element.technology = value
    }

/**
 * @see [InfrastructureNode.uses]
 */
fun ElementScope<InfrastructureNode>.uses(
    destination: DeploymentElement,
    description: String? = null,
    technology: String? = null,
    interactionStyle: InteractionStyle = InteractionStyle.Synchronous,
    tags: Array<String> = emptyArray(),
    block: ApplyBlock<RelationshipScope>? = null
): Relationship? =
    uses(destination, description, technology, interactionStyle, tags, block)
