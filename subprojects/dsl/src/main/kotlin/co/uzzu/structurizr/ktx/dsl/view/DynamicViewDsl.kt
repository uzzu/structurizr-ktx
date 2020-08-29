@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import co.uzzu.structurizr.ktx.dsl.applyIfNotNull
import com.structurizr.model.Element
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView

/**
 * @see [DeploymentView.add]
 */
fun DynamicView.Relationship(
    source: Element,
    destination: Element,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    add(source, "", destination).applyIfNotNull(block)

/**
 * @see [DeploymentView.add]
 */
fun DynamicView.Relationship(
    source: Element,
    description: String,
    destination: Element,
    block: ApplyBlock<RelationshipView>? = null

): RelationshipView =
    add(source, description, destination).applyIfNotNull(block)

/**
 * Call startParallelSequence, and endParallelSequence before and after apply block.
 * @param block [ParallelSequenceScope]
 */
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
    /**
     * @see [DynamicView.add]
     */
    fun Relationship(
        source: Element,
        destination: Element,
        block: ApplyBlock<RelationshipView>? = null
    ): RelationshipView =
        view.Relationship(source, "", destination).applyIfNotNull(block)

    /**
     * @see [DynamicView.add]
     */
    fun Relationship(
        source: Element,
        description: String,
        destination: Element,
        block: ApplyBlock<RelationshipView>? = null
    ): RelationshipView =
        view.Relationship(source, description, destination).applyIfNotNull(block)

    /**
     * @see [DynamicView.parallelSequence]
     */
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
