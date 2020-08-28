@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Element
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView

fun DynamicView.Relationship(
    source: Element,
    destination: Element,
    description: String? = null,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    add(source, description, destination).applyIfNotNull(block)

fun DynamicView.parallelSequence(
    block: ApplyBlock<ParallelSequenceScope>
) {
    startParallelSequence()
    try {
        ParallelSequenceScope(this).apply(block)
    } finally {
        endParallelSequence()
    }
}

class ParallelSequenceScope(
    private val view: DynamicView
) {
    fun Relationship(
        source: Element,
        destination: Element,
        description: String? = null,
        block: ApplyBlock<RelationshipView>? = null
    ): RelationshipView =
        view.Relationship(source, destination, description, block)

    fun parallelSequence(
        block: ApplyBlock<ParallelSequenceScope>
    ) {
        view.startParallelSequence()
        try {
            ParallelSequenceScope(view).apply(block)
        } finally {
            view.endParallelSequence()
        }
    }
}
