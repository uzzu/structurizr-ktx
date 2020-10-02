package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.SoftwareSystemInstance
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.DeploymentView
import com.structurizr.view.RelationshipView

abstract class DeploymentViewBody(
    protected val view: DeploymentView,
    private val config: Config
) {
    fun asText(): String = buildString {
        rankDirection()
        lineSeparator()

        view.elements.asSequence()
            .filter {
                it.element is DeploymentNode &&
                    it.element.parent == null &&
                    view.isElementInView(it.element)
            }
            .map { it.element as DeploymentNode }
            .forEach { deploymentNode(it) }

        view.relationships.forEach {
            relationshipView(it)
            lineSeparator()
        }
    }

    protected abstract fun StringBuilder.rankDirection(rankDirection: AutomaticLayout.RankDirection)

    protected abstract fun StringBuilder.beginScope(deploymentNode: DeploymentNode)

    protected abstract fun StringBuilder.endScope(deploymentNode: DeploymentNode)

    protected abstract fun StringBuilder.infrastructureNode(infrastructureNode: InfrastructureNode, prefix: String)

    protected abstract fun StringBuilder.softwareSystemInstance(softwareSystemInstance: SoftwareSystemInstance, prefix: String)

    protected abstract fun StringBuilder.containerInstance(containerInstance: ContainerInstance, prefix: String)

    protected abstract fun StringBuilder.relationshipView(relationshipView: RelationshipView)

    private fun StringBuilder.rankDirection() {
        val rankDirection = view.automaticLayout?.rankDirection ?: AutomaticLayout.RankDirection.TopBottom
        rankDirection(rankDirection)
    }

    private fun StringBuilder.deploymentNode(deploymentNode: DeploymentNode, indentCount: Int = 0) {
        indent(indentCount)
        beginScope(deploymentNode)
        lineSeparator()

        val childIndentCount = indentCount + 1

        deploymentNode.children.asSequence()
            .filter { view.isElementInView(it) }
            .forEach {
                deploymentNode(it)
            }

        deploymentNode.infrastructureNodes
            .forEach {
                infrastructureNode(it, indentText(childIndentCount))
                lineSeparator()
            }

        deploymentNode.softwareSystemInstances
            .filter { view.isElementInView(it) }
            .forEach {
                softwareSystemInstance(it, indentText(childIndentCount))
                lineSeparator()
            }

        deploymentNode.containerInstances
            .filter { view.isElementInView(it) }
            .forEach {
                containerInstance(it, indentText(childIndentCount))
                lineSeparator()
            }

        indent(indentCount)
        endScope(deploymentNode)
        lineSeparator()
    }

    private fun StringBuilder.indent(count: Int) {
        append(indentText(count))
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }

    private fun indentText(count: Int): String =
        "  ".repeat(count)
}
