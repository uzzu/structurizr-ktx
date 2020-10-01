@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.SoftwareSystemInstance
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
class DeploymentNodeScope
internal constructor(
    element: DeploymentNode
) : ElementScope<DeploymentNode>(element) {

    /**
     * @see [DeploymentNode.technology]
     */
    var technology: String?
        get() = element.technology
        set(value) {
            element.technology = value
        }

    /**
     * @see [DeploymentNode.instances]
     */
    var instances: Int
        get() = element.instances
        set(value) {
            element.instances = value
        }

    /**
     * @see [DeploymentNode.add]
     */
    fun SoftwareSystemInstance(
        softwareSystem: SoftwareSystem,
        replicateRelationships: Boolean = true,
        block: SoftwareSystemInstanceScope.() -> Unit = Any::doNothing
    ): SoftwareSystemInstance {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.add(softwareSystem, replicateRelationships)
            .apply { block(SoftwareSystemInstanceScope(this)) }
    }

    /**
     * @see [DeploymentNode.add]
     */
    fun ContainerInstance(
        container: Container,
        replicateRelationships: Boolean = true,
        block: ContainerInstanceScope.() -> Unit = Any::doNothing
    ): ContainerInstance {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.add(container, replicateRelationships)
            .apply { block(ContainerInstanceScope(this)) }
    }

    /**
     * @see [DeploymentNode.addDeploymentNode]
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

        return element.addDeploymentNode(name, description, technology, instances, properties)
            .apply { block(DeploymentNodeScope(this)) }
    }

    /**
     * @see [DeploymentNode.addInfrastructureNode]
     */
    fun InfrastructureNode(
        name: String,
        description: String? = null,
        technology: String? = null,
        properties: Map<String, String>? = null,
        block: InfrastructureNodeScope.() -> Unit = Any::doNothing
    ): InfrastructureNode {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return element.addInfrastructureNode(name, description, technology, properties)
            .apply { block(InfrastructureNodeScope(this)) }
    }
}
