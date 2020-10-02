package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.view.ComponentView
import com.structurizr.view.ContainerView
import com.structurizr.view.DeploymentView
import com.structurizr.view.DynamicView
import com.structurizr.view.SystemContextView
import com.structurizr.view.SystemLandscapeView
import com.structurizr.view.View

interface PlantUmlEngine {
    val config: Config
    fun header(view: View): Header = Header(view, config)
    fun footer(view: View): Footer = Footer(config)
    fun systemLandscapeViewBody(view: SystemLandscapeView): SystemLandscapeViewBody
    fun systemContextViewBody(view: SystemContextView): SystemContextViewBody
    fun containerViewBody(view: ContainerView): ContainerViewBody
    fun componentViewBody(view: ComponentView): ComponentViewBody
    fun dynamicViewBody(view: DynamicView): DynamicViewBody
    fun deploymentViewBody(view: DeploymentView): DeploymentViewBody
}

interface PlantUmlEngineFactory<TConfig : Config> {
    fun create(block: ConfigScope<TConfig>.() -> Unit): PlantUmlEngine
}
