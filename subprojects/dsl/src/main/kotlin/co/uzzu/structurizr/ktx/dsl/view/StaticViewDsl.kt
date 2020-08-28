package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Element
import com.structurizr.view.StaticView

fun StaticView.animations(
    block: ApplyBlock<StaticViewAnimationScope>
) {
    StaticViewAnimationScope(this).apply(block)
}

class StaticViewAnimationScope(
    private val view: StaticView
) {

    fun step(vararg elements: Element) {
        view.addAnimation(*elements)
    }
}
