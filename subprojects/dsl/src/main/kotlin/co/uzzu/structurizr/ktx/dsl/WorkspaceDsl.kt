package co.uzzu.structurizr.ktx.dsl

import com.structurizr.Workspace
import com.structurizr.model.Model
import com.structurizr.view.ViewSet

fun Workspace.model(block: ApplyBlock<Model>): Model =
    model.apply(block)

fun Workspace.views(block: ApplyBlock<ViewSet>): ViewSet =
    views.apply(block)
