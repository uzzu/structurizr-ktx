@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.DeploymentNode
import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem

/**
 * @see [Model.addSoftwareSystem]
 */
fun Model.SoftwareSystem(
    name: String,
    block: ApplyBlock<SoftwareSystem>? = null
): SoftwareSystem =
    addSoftwareSystem(name).applyIfNotNull(block)

/**
 * @see [Model.addSoftwareSystem]
 */
fun Model.SoftwareSystem(
    name: String,
    description: String?,
    block: ApplyBlock<SoftwareSystem>? = null
): SoftwareSystem =
    addSoftwareSystem(name, description).applyIfNotNull(block)

/**
 * @see [Model.addSoftwareSystem]
 */
fun Model.SoftwareSystem(
    location: Location,
    name: String,
    description: String?,
    block: ApplyBlock<SoftwareSystem>? = null
): SoftwareSystem =
    addSoftwareSystem(location, name, description).applyIfNotNull(block)

/**
 * @see [Model.addPerson]
 */
fun Model.Person(
    name: String,
    block: ApplyBlock<Person>? = null
): Person =
    addPerson(name).applyIfNotNull(block)

/**
 * @see [Model.addPerson]
 */
fun Model.Person(
    name: String,
    description: String?,
    block: ApplyBlock<Person>? = null
): Person =
    addPerson(name, description).applyIfNotNull(block)

/**
 * @see [Model.addPerson]
 */
fun Model.Person(
    location: Location,
    name: String,
    description: String?,
    block: ApplyBlock<Person>? = null
): Person =
    addPerson(location, name, description).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    name: String,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    name: String,
    description: String?,
    technology: String,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    environment: String?,
    name: String,
    description: String?,
    technology: String?,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(environment, name, description, technology).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology, instances).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    environment: String?,
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(environment, name, description, technology, instances).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    properties: Map<String, String>,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(name, description, technology, instances, properties).applyIfNotNull(block)

/**
 * @see [Model.addDeploymentNode]
 */
fun Model.DeploymentNode(
    environment: String?,
    name: String,
    description: String?,
    technology: String?,
    instances: Int,
    properties: Map<String, String>,
    block: ApplyBlock<DeploymentNode>? = null
): DeploymentNode =
    addDeploymentNode(environment, name, description, technology, instances, properties).applyIfNotNull(block)
