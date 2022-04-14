@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import co.uzzu.structurizr.ktx.dsl.doNothing
import com.structurizr.model.Element
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@StructurizrDslMarker
class DynamicViewScope
internal constructor(
    view: DynamicView
) : ViewScope<DynamicView, DynamicViewInclusionScope, DynamicViewExclusionScope>(
    view,
    DynamicViewInclusionScope(view),
    DynamicViewExclusionScope(view)
)

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class DynamicViewInclusionScope
internal constructor(
    view: DynamicView
) : InclusionScope<DynamicView>(view) {

    /**
     * @see [DynamicView.add]
     */
    fun rel(
        pair: Pair<Element, Element>,
        description: String = "",
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [DynamicView.add]
     */
    fun relationship(
        pair: Pair<Element, Element>,
        description: String = "",
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        return pair.let { (source, destination) ->
            view.add(source, description, destination)
                .apply { block(RelationshipViewScope(this)) }
        }
    }

    /**
     * Call startParallelSequence, and endParallelSequence before and after apply block.
     * @param block [ParallelSequenceScope]
     */
    fun parallelSequence(
        block: ParallelSequenceScope.() -> Unit
    ) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        view.startParallelSequence()
        try {
            ParallelSequenceScope(this).apply(block)
        } finally {
            view.endParallelSequence()
        }
    }
}

@StructurizrDslMarker
class DynamicViewExclusionScope
internal constructor(
    view: DynamicView
) : ExclusionScope<DynamicView>(view)

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class ParallelSequenceScope
internal constructor(
    private val scope: DynamicViewInclusionScope
) {
    /**
     * @see [DynamicView.add]
     */
    fun rel(
        pair: Pair<Element, Element>,
        description: String = "",
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return relationship(pair, description, block)
    }

    /**
     * @see [DynamicView.add]
     */
    fun relationship(
        pair: Pair<Element, Element>,
        description: String = "",
        block: RelationshipViewScope.() -> Unit = Any::doNothing
    ): RelationshipView {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return scope.relationship(pair, description, block)
    }

    /**
     * @see [DynamicView.parallelSequence]
     */
    fun parallelSequence(
        block: ParallelSequenceScope.() -> Unit
    ) {
        scope.parallelSequence(block)
    }
}
