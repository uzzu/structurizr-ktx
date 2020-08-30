@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl

import co.uzzu.structurizr.ktx.dsl.model.ModelScope
import co.uzzu.structurizr.ktx.dsl.view.ViewSetScope
import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.view.ViewSet

/**
 * @see [Workspace]
 */
fun Workspace(
    name: String,
    description: String,
    block: ApplyBlock<WorkspaceScope>
): Workspace =
    Workspace(name, description)
        .apply { WorkspaceScope(this).apply(block) }

/**
 * @see [Workspace.model]
 * @param block [ModelScope]
 */
fun Workspace.model(block: ApplyBlock<Model>): Model =
    model.apply(block)

/**
 * @see [Workspace.views]
 * @param block [ViewSetScope]
 */
fun Workspace.views(block: ApplyBlock<ViewSet>): ViewSet =
    views.apply(block)

class WorkspaceScope(
    private val workspace: Workspace
) {
    fun model(block: ApplyBlock<ModelScope>): Model =
        workspace.model.apply { ModelScope(this).apply(block) }

    fun views(block: ApplyBlock<ViewSetScope>): ViewSet =
        workspace.views.apply { ViewSetScope(this).apply(block) }
}
