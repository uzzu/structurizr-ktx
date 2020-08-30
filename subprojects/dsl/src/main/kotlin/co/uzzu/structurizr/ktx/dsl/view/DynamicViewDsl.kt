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
fun InclusionScope<DynamicView>.relationship(
    source: Element,
    destination: Element,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    view.add(source, "", destination).applyIfNotNull(block)

/**
 * @see [DeploymentView.add]
 */
fun InclusionScope<DynamicView>.relationship(
    source: Element,
    description: String,
    destination: Element,
    block: ApplyBlock<RelationshipView>? = null
): RelationshipView =
    view.add(source, description, destination).applyIfNotNull(block)

/**
 * Call startParallelSequence, and endParallelSequence before and after apply block.
 * @param block [ParallelSequenceScope]
 */
fun InclusionScope<DynamicView>.parallelSequence(
    block: ApplyBlock<ParallelSequenceScope>
) {
    view.startParallelSequence()
    try {
        ParallelSequenceScope(this).apply(block)
    } finally {
        view.endParallelSequence()
    }
}

class ParallelSequenceScope(
    private val scope: InclusionScope<DynamicView>
) {
    /**
     * @see [DynamicView.add]
     */
    fun relationship(
        source: Element,
        destination: Element,
        block: ApplyBlock<RelationshipView>? = null
    ): RelationshipView =
        scope.relationship(source, destination, block)

    /**
     * @see [DynamicView.add]
     */
    fun relationship(
        source: Element,
        description: String,
        destination: Element,
        block: ApplyBlock<RelationshipView>? = null
    ): RelationshipView =
        scope.relationship(source, description, destination, block)

    /**
     * @see [DynamicView.parallelSequence]
     */
    fun parallelSequence(
        block: ApplyBlock<ParallelSequenceScope>
    ) {
        scope.parallelSequence(block)
    }
}
