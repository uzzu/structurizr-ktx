package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.Workspace
import com.structurizr.view.ComponentView
import com.structurizr.view.ContainerView
import com.structurizr.view.DeploymentView
import com.structurizr.view.DynamicView
import com.structurizr.view.SystemContextView
import com.structurizr.view.SystemLandscapeView
import com.structurizr.view.View
import java.io.Writer

interface PlantUmlWriter {

    fun write(workspace: Workspace, writer: Writer) {
        workspace.views.systemLandscapeViews.forEach { write(it, writer) }
        workspace.views.systemContextViews.forEach { write(it, writer) }
        workspace.views.containerViews.forEach { write(it, writer) }
        workspace.views.componentViews.forEach { write(it, writer) }
        workspace.views.dynamicViews.forEach { write(it, writer) }
        workspace.views.deploymentViews.forEach { write(it, writer) }
    }

    fun write(view: SystemLandscapeView, writer: Writer)
    fun write(view: SystemContextView, writer: Writer)
    fun write(view: ContainerView, writer: Writer)
    fun write(view: ComponentView, writer: Writer)
    fun write(view: DynamicView, writer: Writer)
    fun write(view: DeploymentView, writer: Writer)
}

@Suppress("FunctionName")
fun <TConfig : Config> PlantUmlWriter(
    factory: PlantUmlEngineFactory<TConfig>,
    block: (ConfigScope<TConfig>.() -> Unit)? = null
): PlantUmlWriter {
    val engine = factory.create { block?.invoke(this) }
    return PlantUmlWriterImpl(engine)
}

private class PlantUmlWriterImpl<TEngine : PlantUmlEngine>(
    private val engine: TEngine
) : PlantUmlWriter {

    override fun write(view: SystemLandscapeView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            systemLandscapeViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    override fun write(view: SystemContextView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            systemContextViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    override fun write(view: ContainerView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            containerViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    override fun write(view: ComponentView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            componentViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    override fun write(view: DynamicView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            dynamicViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    override fun write(view: DeploymentView, writer: Writer) {
        val text = buildString {
            header(view, engine)
            deploymentViewBody(view, engine)
            footer(view, engine)
        }
        runCatching { writer.write(text) }.onFailure(Throwable::printStackTrace)
    }

    private fun StringBuilder.header(view: View, engine: TEngine) {
        append(engine.header(view).asText())
    }

    private fun StringBuilder.footer(view: View, engine: TEngine) {
        append(engine.footer(view).asText())
    }

    private fun StringBuilder.systemLandscapeViewBody(view: SystemLandscapeView, engine: TEngine) {
        append(engine.systemLandscapeViewBody(view).asText())
    }

    private fun StringBuilder.systemContextViewBody(view: SystemContextView, engine: TEngine) {
        append(engine.systemContextViewBody(view).asText())
    }

    private fun StringBuilder.containerViewBody(view: ContainerView, engine: TEngine) {
        append(engine.containerViewBody(view).asText())
    }

    private fun StringBuilder.componentViewBody(view: ComponentView, engine: TEngine) {
        append(engine.componentViewBody(view).asText())
    }

    private fun StringBuilder.dynamicViewBody(view: DynamicView, engine: TEngine) {
        append(engine.dynamicViewBody(view).asText())
    }

    private fun StringBuilder.deploymentViewBody(view: DeploymentView, engine: TEngine) {
        append(engine.deploymentViewBody(view).asText())
    }
}
