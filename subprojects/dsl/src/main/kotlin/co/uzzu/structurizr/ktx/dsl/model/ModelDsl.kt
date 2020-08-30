@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.DeploymentNode
import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem

class ModelScope(
    private val model: Model
) {
    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        name: String,
        description: String? = null,
        block: ApplyBlock<ElementScope<SoftwareSystem>>? = null
    ): SoftwareSystem =
        model.addSoftwareSystem(name, description)
            .apply { block?.let { ElementScope(this).apply(it) } }

    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        location: Location,
        name: String,
        description: String? = null,
        block: ApplyBlock<ElementScope<SoftwareSystem>>? = null
    ): SoftwareSystem =
        model.addSoftwareSystem(location, name, description)
            .apply { block?.let { ElementScope(this).apply(it) } }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        name: String,
        description: String? = null,
        block: ApplyBlock<ElementScope<Person>>? = null
    ): Person =
        model.addPerson(name, description)
            .apply { block?.let { ElementScope(this).apply(it) } }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        location: Location,
        name: String,
        description: String?,
        block: ApplyBlock<ElementScope<Person>>? = null
    ): Person =
        model.addPerson(location, name, description)
            .apply { block?.let { ElementScope(this).apply(it) } }

    /**
     * @see [Model.addDeploymentNode]
     */
    fun DeploymentNode(
        name: String,
        description: String? = null,
        technology: String? = null,
        instances: Int = 1,
        properties: Map<String, String>? = null,
        block: ApplyBlock<ElementScope<DeploymentNode>>? = null
    ): DeploymentNode =
        model.addDeploymentNode(name, description, technology, instances, properties)
            .apply { block?.let { ElementScope(this).apply(it) } }

    /**
     * @see [Model.addDeploymentNode]
     */
    fun DeploymentNode(
        environment: String?,
        name: String,
        description: String? = null,
        technology: String? = null,
        instances: Int = 1,
        properties: Map<String, String>? = null,
        block: ApplyBlock<ElementScope<DeploymentNode>>? = null
    ): DeploymentNode =
        model.addDeploymentNode(environment, name, description, technology, instances, properties)
            .apply { block?.let { ElementScope(this).apply(it) } }
}
