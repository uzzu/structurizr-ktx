@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.SoftwareSystemInstance

/**
 * @see [DeploymentNode.add]
 */
fun DeploymentNode.ContainerInstance(
    container: Container,
    replicateRelationships: Boolean = true,
    block: ApplyBlock<ContainerInstance>? = null
): ContainerInstance =
    add(container, replicateRelationships).applyIfNotNull(block)

/**
 * @see [DeploymentNode.add]
 */
fun DeploymentNode.SoftwareSystemInstance(
    softwareSystem: SoftwareSystem,
    replicateRelationships: Boolean = true,
    block: ApplyBlock<SoftwareSystemInstance>? = null
): SoftwareSystemInstance =
    add(softwareSystem, replicateRelationships).applyIfNotNull(block)

/**
 * @see [DeploymentNode.add]
 */
fun DeploymentNode.DeploymentNode(
    name: String,
    description: String? = null,
    technology: String? = null,
    instances: Int = 1,
    properties: Map<String, String>? = null,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology, instances, properties).applyIfNotNull(block)

/**
 * @see [DeploymentNode.add]
 */
fun DeploymentNode.InfrastructureNode(
    name: String,
    description: String? = null,
    technology: String? = null,
    properties: Map<String, String>? = null,
    block: ApplyBlock<InfrastructureNode>? = null
): InfrastructureNode =
    addInfrastructureNode(name, description, technology, properties).applyIfNotNull(block)
