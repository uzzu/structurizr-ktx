package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.StaticStructureElementInstance
import com.structurizr.view.DeploymentView

/**
 * @param block [DeploymentViewAnimationScope]
 */
fun DeploymentView.animations(
    block: ApplyBlock<DeploymentViewAnimationScope>
) {
    DeploymentViewAnimationScope(this).apply(block)
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
