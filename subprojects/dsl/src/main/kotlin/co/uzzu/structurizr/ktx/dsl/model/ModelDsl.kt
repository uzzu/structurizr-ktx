@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.DeploymentNode
import com.structurizr.model.Location
import com.structurizr.model.Model
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ModelScope
internal constructor(
    private val model: Model
) : ModelRelationshipWritable {
    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        name: String,
        description: String? = null,
        block: SoftwareSystemScope.() -> Unit = Any::doNothing
    ): SoftwareSystem {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addSoftwareSystem(name, description)
            .apply { block(SoftwareSystemScope(this)) }
    }

    /**
     * @see [Model.addSoftwareSystem]
     */
    fun SoftwareSystem(
        location: Location,
        name: String,
        description: String? = null,
        block: SoftwareSystemScope.() -> Unit = Any::doNothing
    ): SoftwareSystem {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addSoftwareSystem(location, name, description)
            .apply { block(SoftwareSystemScope(this)) }
    }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        name: String,
        description: String? = null,
        block: PersonScope.() -> Unit = Any::doNothing
    ): Person {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addPerson(name, description)
            .apply { block(PersonScope(this)) }
    }

    /**
     * @see [Model.addPerson]
     */
    fun Person(
        location: Location,
        name: String,
        description: String? = null,
        block: PersonScope.() -> Unit = Any::doNothing
    ): Person {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addPerson(location, name, description)
            .apply { block(PersonScope(this)) }
    }

    /**
     * @see [Model.addDeploymentNode]
     */
    fun DeploymentNode(
        name: String,
        description: String? = null,
        technology: String? = null,
        instances: Int = 1,
        properties: Map<String, String>? = null,
        block: DeploymentNodeScope.() -> Unit = Any::doNothing
    ): DeploymentNode {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addDeploymentNode(name, description, technology, instances, properties)
            .apply { block(DeploymentNodeScope(this)) }
    }

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
        block: DeploymentNodeScope.() -> Unit = Any::doNothing
    ): DeploymentNode {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return model.addDeploymentNode(environment, name, description, technology, instances, properties)
            .apply { block(DeploymentNodeScope(this)) }
    }
}
