package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.Relationship
import com.structurizr.model.StaticStructureElementInstance
import com.structurizr.view.DeploymentView
import com.structurizr.view.RelationshipView

// region ViewScope

/**
 * @see [DeploymentView.setEnvironment]
 */
var ViewScope<DeploymentView>.environment: String
    get() = view.environment
    set(value) {
        view.environment = value
    }

/**
 * @param block [DeploymentViewAnimationScope]
 */
fun ViewScope<DeploymentView>.animations(
    block: ApplyBlock<DeploymentViewAnimationScope>
) {
    DeploymentViewAnimationScope(view).apply(block)
}

class DeploymentViewAnimationScope(
    private val view: DeploymentView
) {
    /**
     * @see [DeploymentView.addAnimation]
     */
    fun step(vararg infrastructureNodes: InfrastructureNode) {
        view.addAnimation(*infrastructureNodes)
    }

    /**
     * @see [DeploymentView.addAnimation]
     */
    fun step(vararg staticStructureElementInstances: StaticStructureElementInstance) {
        view.addAnimation(*staticStructureElementInstances)
    }

    /**
     * @see [DeploymentView.addAnimation]
     */
    fun step(
        infrastructureNodes: Array<InfrastructureNode>,
        staticStructureElementInstances: Array<StaticStructureElementInstance>
    ) {
        view.addAnimation(staticStructureElementInstances, infrastructureNodes)
    }
}

// endregion

// region InclusionScope

/**
 * @see [DeploymentView.addAllDeploymentNodes]
 */
fun InclusionScope<DeploymentView>.allDeploymentNodes() {
    view.addAllDeploymentNodes()
}

/**
 * @see [DeploymentView.add]
 */
fun InclusionScope<DeploymentView>.deploymentNode(
    deploymentNode: DeploymentNode,
    addRelationship: Boolean = true
) {
    view.add(deploymentNode, addRelationship)
}

/**
 * @see [DeploymentView.add]
 */
fun InclusionScope<DeploymentView>.relationship(
    relationship: Relationship,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    view.add(relationship)
        .applyIfNotNull(block)

// endregion

// region ExclusionScope

/**
 * @see [DeploymentView.remove]
 */
fun ExclusionScope<DeploymentView>.deploymentNode(deploymentNode: DeploymentNode) {
    view.remove(deploymentNode)
}

/**
 * @see [DeploymentView.remove]
 */
fun ExclusionScope<DeploymentView>.infrastructureNode(infrastructureNode: InfrastructureNode) {
    view.remove(infrastructureNode)
}

/**
 * @see [DeploymentView.remove]
 */
fun ExclusionScope<DeploymentView>.containerInstance(containerInstance: ContainerInstance) {
    view.remove(containerInstance)
}

// endregion
