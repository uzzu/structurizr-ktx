@file:Suppress("FunctionName")

package co.uzzu.structurizr.ktx.dsl

import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.view.ViewSet

/**
 * @see [Workspace]
 */
fun Workspace(
    name: String,
    description: String,
    block: ApplyBlock<Workspace>
): Workspace =
    Workspace(name, description).applyIfNotNull(block)

/**
 * @see [Workspace.model]
 */
fun Workspace.model(block: ApplyBlock<Model>): Model =
    model.apply(block)

/**
 * @see [Workspace.views]
 */
fun Workspace.views(block: ApplyBlock<ViewSet>): ViewSet =
    views.apply(block)
