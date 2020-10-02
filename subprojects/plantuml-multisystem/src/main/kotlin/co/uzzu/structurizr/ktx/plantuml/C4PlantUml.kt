package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.Element
import com.structurizr.model.Enterprise
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.Location
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.SoftwareSystemInstance
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.ComponentView
import com.structurizr.view.ContainerView
import com.structurizr.view.DeploymentView
import com.structurizr.view.DynamicView
import com.structurizr.view.RelationshipView
import com.structurizr.view.SystemContextView
import com.structurizr.view.SystemLandscapeView

object C4PlantUml : PlantUmlEngineFactory<C4PlantUmlConfig> {
    override fun create(block: ConfigScope<C4PlantUmlConfig>.() -> Unit): PlantUmlEngine {
        val config = C4PlantUmlConfig()
        val scope = ConfigScope(config)
        block(scope)
        scope.apply {
            when (val definitionRoot = config.definitionRoot) {
                is C4DefinitionRoot.PlantUml -> {
                    definitionRoot.values().forEach { includeFile(it) }
                }
                is C4DefinitionRoot.PathDefined -> {
                    definitionRoot.values().forEach { includeFile(it) }
                }
                is C4DefinitionRoot.UrlDefined -> {
                    definitionRoot.values().forEach { includeUrl(it) }
                }
            }
        }
        return C4PlantUmlEngine(config)
    }
}

sealed class C4ElementType(val value: String) {
    object Default : C4ElementType("")
    object Db : C4ElementType("Db")

    companion object {
        internal val propertyName = "c4:element:type"

        internal fun of(value: String?): C4ElementType =
            when (value) {
                "Db" -> Db
                else -> Default
            }
    }
}

sealed class C4RelationshipMode(
    val value: String,
    val macroBase: String = "Rel_$value"
) {
    object Rel : C4RelationshipMode("Rel", "Rel")
    object Neighbor : C4RelationshipMode("Neighbor")
    object Back : C4RelationshipMode("Back")
    object BackNeighbor : C4RelationshipMode("Back_Neighbor")
    object Lay : C4RelationshipMode("Lay", "Lay")

    companion object {
        internal val propertyName = "c4:layout:mode"

        internal fun of(value: String?): C4RelationshipMode =
            when (value) {
                "Neighbor" -> Neighbor
                "Back" -> Back
                "Back_Neighbor" -> BackNeighbor
                "Lay" -> Lay
                else -> Rel
            }
    }
}

sealed class C4Direction(
    val value: String,
    val macroSuffix: String = "_${value.substring(0, 1)}"
) {
    object Default : C4Direction("", "")
    object Up : C4Direction("Up")
    object Down : C4Direction("Down")
    object Right : C4Direction("Right")
    object Left : C4Direction("Left")

    companion object {
        internal val propertyName = "c4:layout:direction"

        internal fun of(value: String?): C4Direction =
            when (value) {
                "Up" -> Up
                "Down" -> Down
                "Right" -> Right
                "Left" -> Left
                else -> Default
            }
    }
}

private class C4PlantUmlEngine(
    override val config: C4PlantUmlConfig
) : PlantUmlEngine {

    override fun systemLandscapeViewBody(view: SystemLandscapeView) =
        C4SystemLandscapeViewBody(view, config)

    override fun systemContextViewBody(view: SystemContextView) =
        C4SystemContextViewBody(view, config)

    override fun containerViewBody(view: ContainerView) =
        C4ContainerViewBody(view, config)

    override fun componentViewBody(view: ComponentView) =
        C4ComponentViewBody(view, config)

    override fun dynamicViewBody(view: DynamicView) =
        C4DynamicViewBody(view, config)

    override fun deploymentViewBody(view: DeploymentView): DeploymentViewBody =
        C4DeploymentViewBody(view, config)
}

private class C4SystemLandscapeViewBody(
    view: SystemLandscapeView,
    private val config: C4PlantUmlConfig
) : SystemLandscapeViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.person(person: Person) {
        append(person.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.softwareSystem(softwareSystem: SoftwareSystem) {
        append(softwareSystem.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.beginScope(enterprise: Enterprise?) {
        append(enterprise?.asC4ScopeBeginning())
    }

    override fun StringBuilder.endScope(enterprise: Enterprise?) {
        append(scopeEnding)
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        append(relationshipView.asC4Text(config.escapedLineSeparator))
    }
}

private class C4SystemContextViewBody(
    view: SystemContextView,
    private val config: C4PlantUmlConfig
) : SystemContextViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.person(person: Person) {
        append(person.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.softwareSystem(softwareSystem: SoftwareSystem) {
        append(softwareSystem.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.beginScope(enterprise: Enterprise?) {
        append(enterprise?.asC4ScopeBeginning())
    }

    override fun StringBuilder.endScope(enterprise: Enterprise?) {
        append(scopeEnding)
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        append(relationshipView.asC4Text(config.escapedLineSeparator))
    }
}

private class C4ContainerViewBody(
    view: ContainerView,
    private val config: C4PlantUmlConfig
) : ContainerViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.beginScope(softwareSystem: SoftwareSystem) {
        append("""System_Boundary(${softwareSystem.id}, "${softwareSystem.name}") {""")
    }

    override fun StringBuilder.endScope(softwareSystem: SoftwareSystem) {
        append(scopeEnding)
    }

    override fun StringBuilder.container(container: Container) {
        append(container.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        append(relationshipView.asC4Text(config.escapedLineSeparator))
    }
}

private class C4ComponentViewBody(
    view: ComponentView,
    private val config: C4PlantUmlConfig
) : ComponentViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.beginScope(container: Container) {
        append("""Container_Boundary(${container.id}, "${container.name}") {""")
    }

    override fun StringBuilder.endScope(container: Container) {
        append(scopeEnding)
    }

    override fun StringBuilder.component(component: Component) {
        append(component.asC4Text(config.escapedLineSeparator))
    }

    override fun StringBuilder.elementExceptComponent(element: Element) {
        val escapedLineSeparator = config.escapedLineSeparator
        val text = when (element) {
            is Person -> element.asC4Text(escapedLineSeparator)
            is SoftwareSystem -> element.asC4Text(escapedLineSeparator)
            is Container -> element.asC4Text(escapedLineSeparator)
            is SoftwareSystemInstance -> element.asC4Text(escapedLineSeparator)
            is ContainerInstance -> element.asC4Text(escapedLineSeparator)
            else -> error("Unexpected element: $element")
        }
        append(text)
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        append(relationshipView.asC4Text(config.escapedLineSeparator))
    }
}

private class C4DynamicViewBody(
    view: DynamicView,
    private val config: C4PlantUmlConfig
) : DynamicViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.element(element: Element) {
        val escapedLineSeparator = config.escapedLineSeparator
        val text = when (element) {
            is Person -> element.asC4Text(escapedLineSeparator)
            is SoftwareSystem -> element.asC4Text(escapedLineSeparator)
            is Container -> element.asC4Text(escapedLineSeparator)
            is Component -> element.asC4Text(escapedLineSeparator)
            is SoftwareSystemInstance -> element.asC4Text(escapedLineSeparator)
            is ContainerInstance -> element.asC4Text(escapedLineSeparator)
            else -> throw UnsupportedOperationException("Specified element ${element.javaClass} does not supported.")
        }
        append(text)
    }

    override fun StringBuilder.sequenceElement(element: Element) {
        val style = view.viewSet.configuration.styles.findElementStyle(element)
        val sequenceDiagramConfig = requireNotNull(config.sequenceDiagramConfigs[view.key])
        append(element.asSequenceText(style, sequenceDiagramConfig.useDefaultColor))
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        val style = view.viewSet.configuration.styles.findRelationshipStyle(relationshipView.relationship)
        val useDefaultColor = config.sequenceDiagramConfigs[view.key]?.useDefaultColor ?: false
        append(relationshipView.asDynamicViewText(style, useDefaultColor, config.escapedLineSeparator))
    }
}

private class C4DeploymentViewBody(
    view: DeploymentView,
    private val config: C4PlantUmlConfig
) : DeploymentViewBody(view, config) {

    override fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection) {
        append(rankDirection.asC4Text(config.layoutWithLegend))
    }

    override fun StringBuilder.beginScope(deploymentNode: DeploymentNode) {
        append(deploymentNode.asDeploymentScopeBeginning())
    }

    override fun StringBuilder.endScope(deploymentNode: DeploymentNode) {
        append(scopeEnding)
    }

    override fun StringBuilder.infrastructureNode(
        infrastructureNode: InfrastructureNode,
        prefix: String
    ) {
        val style = view.viewSet.configuration.styles.findElementStyle(infrastructureNode)
        append(infrastructureNode.asDeploymentViewText(style, prefix, config.lineSeparator, config.escapedLineSeparator))
    }

    override fun StringBuilder.softwareSystemInstance(
        softwareSystemInstance: SoftwareSystemInstance,
        prefix: String
    ) {
        append(softwareSystemInstance.asC4Text(config.escapedLineSeparator, prefix = prefix))
    }

    override fun StringBuilder.containerInstance(
        containerInstance: ContainerInstance,
        prefix: String
    ) {
        append(containerInstance.asC4Text(config.escapedLineSeparator, prefix = prefix))
    }

    override fun StringBuilder.relationshipView(relationshipView: RelationshipView) {
        append(relationshipView.asC4Text(config.escapedLineSeparator))
    }
}

private fun Enterprise?.asC4ScopeBeginning(): String {
    val id = this?.name?.hashCode()?.let { "e_$it" } ?: "enterprise_boundary"
    val name = this?.name ?: "Enterprise"
    return """Enterprise_Boundary($id, $name) {"""
}

private fun Person.asC4Text(escapedLineSeparator: String): String {
    val macro = when (location) {
        Location.External -> "Person_Ext"
        else -> "Person"
    }
    return if (description == null) {
        """$macro($id, $name)"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$macro($id, $name, "$description")"""
    }
}

private fun SoftwareSystem.asC4Text(escapedLineSeparator: String): String {
    val type = C4ElementType.of(properties[C4ElementType.propertyName]).value
    val ext = "_Ext".takeIf { location == Location.External } ?: ""
    val macro = "System$type$ext"

    return if (description == null) {
        """$macro($id, "$name")"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$macro($id, "$name", "$description")"""
    }
}

private fun Container.asC4Text(escapedLineSeparator: String): String {
    val type = C4ElementType.of(properties[C4ElementType.propertyName]).value
    val macro = "Container$type"
    val technology = ensureBlankOrEscaping(technology, escapedLineSeparator)

    return if (description == null) {
        """$macro($id, "$name", "$technology")"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$macro($id, "$name", "$technology", "$description")"""
    }
}

private fun Component.asC4Text(escapedLineSeparator: String): String {
    val type = C4ElementType.of(properties[C4ElementType.propertyName]).value
    val macro = "Component$type"
    val technology = ensureBlankOrEscaping(technology, escapedLineSeparator)

    return if (description == null) {
        """$macro($id, "$name", "$technology")"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$macro($id, "$name", "$technology", "$description")"""
    }
}

private fun SoftwareSystemInstance.asC4Text(escapedLineSeparator: String, prefix: String = ""): String {
    val type = C4ElementType.of(properties[C4ElementType.propertyName]).value
    val ext = "_Ext".takeIf { softwareSystem.location == Location.External } ?: ""
    val macro = "System$type$ext"
    val name = softwareSystem.name

    return if (description == null) {
        """$prefix$macro($id, "$name")"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$prefix$macro($id, "$name", "$description")"""
    }
}

private fun ContainerInstance.asC4Text(escapedLineSeparator: String, prefix: String = ""): String {
    val type = C4ElementType.of(properties[C4ElementType.propertyName]).value
    val macro = "Container$type"
    val name = container.name
    val technology = ensureBlankOrEscaping(container.technology, escapedLineSeparator)

    return if (description == null) {
        """$prefix$macro($id, "$name", "$technology")"""
    } else {
        val description = ensureBlankOrEscaping(description, escapedLineSeparator)
        """$prefix$macro($id, "$name", "$technology", "$description")"""
    }
}

private fun RelationshipView.asC4Text(escapedLineSeparator: String): String {
    val (source, destination) = if (isResponse == true /* isResponse is nullable */) {
        Pair(relationship.destination, relationship.source)
    } else {
        Pair(relationship.source, relationship.destination)
    }
    val mode = C4RelationshipMode.of(relationship.properties[C4RelationshipMode.propertyName])
    val direction = C4Direction.of(relationship.properties[C4Direction.propertyName])
    val macro = when (mode) {
        C4RelationshipMode.Rel,
        C4RelationshipMode.Lay -> "${mode.macroBase}${direction.macroSuffix}"
        else -> mode.macroBase
    }

    return if (mode is C4RelationshipMode.Lay) {
        """$macro(${source.id}, ${destination.id})"""
    } else {
        val order = order?.takeIf { it.isNotBlank() }?.let { "$it. " } ?: ""
        val description = ensureBlankOrEscaping(
            description?.takeIf { it.isNotBlank() } ?: relationship.description,
            escapedLineSeparator
        )
        if (relationship.technology == null) {
            """$macro(${source.id}, ${destination.id}, "$order$description")"""
        } else {
            val technology = ensureBlankOrEscaping(relationship.technology, escapedLineSeparator)
            """$macro(${source.id}, ${destination.id}, "$order$description", "$technology")"""
        }
    }
}

private fun AutomaticLayout.RankDirection.asC4Text(layoutWithLegend: Boolean): String =
    if (layoutWithLegend) {
        "LAYOUT_WITH_LEGEND()"
    } else {
        asText()
    }
