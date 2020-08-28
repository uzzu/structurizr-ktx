package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.StaticStructureElementInstance
import com.structurizr.view.DeploymentView

fun DeploymentView.animations(
    block: ApplyBlock<DeploymentViewAnimationScope>
) {
    DeploymentViewAnimationScope(this).apply(block)
}

class DeploymentViewAnimationScope(
    private val view: DeploymentView
) {
    fun step(vararg infrastructureNodes: InfrastructureNode) {
        view.addAnimation(*infrastructureNodes)
    }

    fun step(vararg staticStructureElementInstances: StaticStructureElementInstance) {
        view.addAnimation(*staticStructureElementInstances)
    }

    fun step(
        infrastructureNodes: Array<InfrastructureNode>,
        staticStructureElementInstances: Array<StaticStructureElementInstance>
    ) {
        view.addAnimation(staticStructureElementInstances, infrastructureNodes)
    }
}
