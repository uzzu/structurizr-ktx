package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Element
import com.structurizr.view.StaticView

/**
 * @param block [StaticViewAnimationScope]
 */
fun StaticView.animations(
    block: ApplyBlock<StaticViewAnimationScope>
) {
    StaticViewAnimationScope(this).apply(block)
}

class StaticViewAnimationScope(
    private val view: StaticView
) {

    /**
     * @see [StaticView.addAnimation]
     */
    fun step(vararg elements: Element) {
        view.addAnimation(*elements)
    }
}
