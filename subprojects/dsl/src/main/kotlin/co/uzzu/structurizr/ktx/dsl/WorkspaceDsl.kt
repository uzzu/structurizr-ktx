@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl

import co.uzzu.structurizr.ktx.dsl.model.ModelScope
import co.uzzu.structurizr.ktx.dsl.view.ViewSetScope
import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.view.ViewSet
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @see [Workspace]
 */
@OptIn(ExperimentalContracts::class)
fun Workspace(
    name: String,
    description: String,
    block: WorkspaceScope.() -> Unit
): Workspace {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    return Workspace(name, description)
        .apply { block(WorkspaceScope(this)) }
}

@StructurizrDslMarker
@OptIn(ExperimentalContracts::class)
class WorkspaceScope(
    private val workspace: Workspace
) {
    /**
     * @see [Workspace.model]
     * @param block [ModelScope]
     */
    fun model(block: ModelScope.() -> Unit): Model {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return workspace.model
            .apply { block(ModelScope(this)) }
    }

    /**
     * @see [Workspace.views]
     * @param block [ViewSetScope]
     */
    fun views(block: ViewSetScope.() -> Unit): ViewSet {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }

        return workspace.views.apply { block(ViewSetScope(this)) }
    }
}
