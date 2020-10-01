package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.Relationship
import com.structurizr.model.StaticStructureElementInstance
import com.structurizr.view.DeploymentView
import com.structurizr.view.RelationshipView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
class DeploymentViewScope
internal constructor(
    view: DeploymentView
) : ViewScope<DeploymentView, DeploymentViewInclusionScope, DeploymentViewExclusionScope>(
    view,
    DeploymentViewInclusionScope(view),
    DeploymentViewExclusionScope(view)
) {
    /**
     * @see [DeploymentView.setEnvironment]
     */
    var environment: String
        get() = view.environment
        set(value) {
            view.environment = value
        }

    /**
     * @param block [DeploymentViewAnimationScope]
     */
    fun animations(
        block: DeploymentViewAnimationScope.() -> Unit
    ) {
        DeploymentViewAnimationScope(view).apply(block)
    }
}

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class DeploymentViewInclusionScope
internal constructor(
    view: DeploymentView
) : InclusionScope<DeploymentView>(view) {

    /**
     * @see [DeploymentView.addAllDeploymentNodes]
     */
    fun allDeploymentNodes() {
        view.addAllDeploymentNodes()
    }

    /**
     * @see [DeploymentView.add]
     */
    fun deploymentNode(
        deploymentNode: DeploymentNode,
        addRelationship: Boolean = true
    ) {
        view.add(deploymentNode, addRelationship)
    }

    /**
     * @see [DeploymentView.add]
     */
    fun relationship(
        relationship: Relationship,
        block: RelationshipView.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return view.add(relationship).apply(block)
    }
}

@StructurizrDslMarker
class DeploymentViewExclusionScope
internal constructor(
    view: DeploymentView
) : ExclusionScope<DeploymentView>(view) {

    /**
     * @see [DeploymentView.remove]
     */
    fun deploymentNode(deploymentNode: DeploymentNode) {
        view.remove(deploymentNode)
    }

    /**
     * @see [DeploymentView.remove]
     */
    fun infrastructureNode(infrastructureNode: InfrastructureNode) {
        view.remove(infrastructureNode)
    }

    /**
     * @see [DeploymentView.remove]
     */
    fun containerInstance(containerInstance: ContainerInstance) {
        view.remove(containerInstance)
    }
}

@StructurizrDslMarker
class DeploymentViewAnimationScope
internal constructor(
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
