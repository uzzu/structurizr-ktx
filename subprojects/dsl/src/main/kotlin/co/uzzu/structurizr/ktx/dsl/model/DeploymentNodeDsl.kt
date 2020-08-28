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

fun DeploymentNode.ContainerInstance(
    container: Container,
    block: ApplyBlock<ContainerInstance>? = null
): ContainerInstance =
    add(container).applyIfNotNull(block)

fun DeploymentNode.ContainerInstance(
    container: Container,
    replicateRelationships: Boolean,
    block: ApplyBlock<ContainerInstance>? = null
): ContainerInstance =
    add(container, replicateRelationships).applyIfNotNull(block)

fun DeploymentNode.SoftwareSystemInstance(
    softwareSystem: SoftwareSystem,
    block: ApplyBlock<SoftwareSystemInstance>? = null
): SoftwareSystemInstance =
    add(softwareSystem).applyIfNotNull(block)

fun DeploymentNode.SoftwareSystemInstance(
    softwareSystem: SoftwareSystem,
    replicateRelationships: Boolean,
    block: ApplyBlock<SoftwareSystemInstance>? = null
): SoftwareSystemInstance =
    add(softwareSystem, replicateRelationships).applyIfNotNull(block)

fun DeploymentNode.DeploymentNode(
    name: String,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name).applyIfNotNull(block)

fun DeploymentNode.DeploymentNode(
    name: String,
    description: String?,
    technology: String?,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology).applyIfNotNull(block)

fun DeploymentNode.DeploymentNode(
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology, instances).applyIfNotNull(block)

fun DeploymentNode.DeploymentNode(
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    properties: Map<String, String>,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology, instances, properties).applyIfNotNull(block)

fun DeploymentNode.InfrastructureNode(
    name: String,
    block: ApplyBlock<InfrastructureNode>? = null
): InfrastructureNode =
    addInfrastructureNode(name).applyIfNotNull(block)

fun DeploymentNode.InfrastructureNode(
    name: String,
    description: String?,
    technology: String?,
    block: ApplyBlock<InfrastructureNode>? = null
): InfrastructureNode =
    addInfrastructureNode(name, description, technology).applyIfNotNull(block)

fun DeploymentNode.InfrastructureNode(
    name: String,
    description: String?,
    technology: String?,
    properties: Map<String, String>,
    block: ApplyBlock<InfrastructureNode>? = null
): InfrastructureNode =
    addInfrastructureNode(name, description, technology, properties).applyIfNotNull(block)
