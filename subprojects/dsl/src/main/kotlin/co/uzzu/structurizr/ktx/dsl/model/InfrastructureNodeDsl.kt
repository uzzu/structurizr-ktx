package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
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
    block: ApplyBlock<Relationship>? = null
): Relationship =
    uses(destination, description, technology, interactionStyle, tags).applyIfNotNull(block)
