@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.SoftwareSystemInstance

/**
 * @see [DeploymentNode.technology]
 */
var ElementScope<DeploymentNode>.technology: String?
    get() = element.technology
    set(value) {
        element.technology = value
    }

/**
 * @see [DeploymentNode.instances]
 */
var ElementScope<DeploymentNode>.instances: Int
    get() = element.instances
    set(value) {
        element.instances = value
    }

/**
 * @see [DeploymentNode.add]
 */
fun ElementScope<DeploymentNode>.SoftwareSystemInstance(
    softwareSystem: SoftwareSystem,
    replicateRelationships: Boolean = true,
    block: ApplyBlock<ElementScope<SoftwareSystemInstance>>? = null
): SoftwareSystemInstance =
    element.add(softwareSystem, replicateRelationships)
        .apply { block?.let { ElementScope(this).apply(it) } }

/**
 * @see [DeploymentNode.add]
 */
fun ElementScope<DeploymentNode>.ContainerInstance(
    container: Container,
    replicateRelationships: Boolean = true,
    block: ApplyBlock<ElementScope<ContainerInstance>>? = null
): ContainerInstance =
    element.add(container, replicateRelationships)
        .apply { block?.let { ElementScope(this).apply(it) } }

/**
 * @see [DeploymentNode.addDeploymentNode]
 */
fun ElementScope<DeploymentNode>.DeploymentNode(
    name: String,
    description: String? = null,
    technology: String? = null,
    instances: Int = 1,
    properties: Map<String, String>? = null,
    block: ApplyBlock<ElementScope<DeploymentNode>>? = null
): DeploymentNode =
    element.addDeploymentNode(name, description, technology, instances, properties)
        .apply { block?.let { ElementScope(this).apply(block) } }

/**
 * @see [DeploymentNode.addInfrastructureNode]
 */
fun ElementScope<DeploymentNode>.InfrastructureNode(
    name: String,
    description: String? = null,
    technology: String? = null,
    properties: Map<String, String>? = null,
    block: ApplyBlock<ElementScope<InfrastructureNode>>? = null
): InfrastructureNode =
    element.addInfrastructureNode(name, description, technology, properties)
        .apply { block?.let { ElementScope(this).apply(block) } }
