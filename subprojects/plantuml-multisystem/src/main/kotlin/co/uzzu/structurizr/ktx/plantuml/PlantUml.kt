package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.ContainerInstance
import com.structurizr.model.DeploymentNode
import com.structurizr.model.Element
import com.structurizr.model.InfrastructureNode
import com.structurizr.model.SoftwareSystem
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.ElementStyle
import com.structurizr.view.RelationshipStyle
import com.structurizr.view.RelationshipView
import com.structurizr.view.Shape

internal fun Element.asSequenceText(
    style: ElementStyle,
    useDefaultColor: Boolean
): String {
    val sequenceType = style.shape.asSequenceType()
    val stereoType = asStereoType()
    val background = style.background.takeIf { !useDefaultColor }?.let { " $it" } ?: ""
    return """$sequenceType "$name" as $id <<$stereoType>>$background"""
}

internal fun RelationshipView.asDynamicViewText(
    style: RelationshipStyle,
    useDefaultColor: Boolean,
    escapedLineSeparator: String
): String {
    val relationship = relationship
    val (source, destination, arrowEnd) = if (isResponse == true /* isResponse is nullable */) {
        Triple(relationship.destination, relationship.source, "->")
    } else {
        Triple(relationship.source, relationship.destination, ">")
    }
    val color = style.color.takeIf { !useDefaultColor }?.let { "[$it]" } ?: ""
    val order = order?.takeIf { it.isNotBlank() }?.let { "$it. " } ?: ""
    val description = ensureBlankOrEscaping(
        description ?: relationship.description,
        escapedLineSeparator
    )
    return """${source.id} -$color$arrowEnd ${destination.id} : $order$description"""
}

internal fun DeploymentNode.asDeploymentScopeBeginning(): String {
    val instances = if (instances > 1) {
        "(x$instances)"
    } else {
        ""
    }
    val stereoType = asStereoType()

    return """node "$name$instances" <<$stereoType>> as $id {"""
}

internal fun InfrastructureNode.asDeploymentViewText(
    style: ElementStyle,
    prefix: String,
    lineSeparator: String,
    escapedLineSeparator: String
): String {
    val shapeType = asShapeType(style)
    val background = style.background ?: ""
    val stereoType = asStereoType()

    return buildString {
        append("""$prefix$shapeType $id <<$stereoType>> $background [$lineSeparator""")
        append("""$prefix  $name$lineSeparator""")
        if (description?.isNotEmpty() == true /* description is nullable */) {
            val description = ensureBlankOrEscaping(description, escapedLineSeparator)
            append("$prefix  --$lineSeparator")
            append("$prefix  $description$lineSeparator")
        }

        append("$prefix]")
    }
}

internal fun Element.asShapeType(style: ElementStyle): String =
    if (this is DeploymentNode) {
        "node"
    } else {
        style.shape.asShapeType()
    }

internal fun Element.asStereoType(): String = when (this) {
    is SoftwareSystem -> {
        "Software System"
    }
    is Container -> {
        """Container${technology?.takeIf { it.isNotEmpty() }?.let { ": $it" } ?: ""}"""
    }
    is Component -> {
        """Component${technology?.takeIf { it.isNotEmpty() }?.let { ": $it" } ?: ""}"""
    }
    is DeploymentNode -> {
        """Deployment Node${technology?.takeIf { it.isNotEmpty() }?.let { ": $it" } ?: ""}"""
    }
    is InfrastructureNode -> {
        """Infrastructure Node${technology?.takeIf { it.isNotEmpty() }?.let { ": $it" } ?: ""}"""
    }
    is ContainerInstance -> {
        """Container${container.technology?.takeIf { it.isNotEmpty() }?.let { ": $it" } ?: ""}"""
    }
    else -> {
        javaClass.simpleName
    }
}

internal fun AutomaticLayout.RankDirection.asText(): String = when (this) {
    AutomaticLayout.RankDirection.LeftRight -> "left to right direction"
    else -> "top to bottom direction"
}

internal fun Shape.asSequenceType(): String = when (this) {
    Shape.Person -> "actor"
    else -> "participant"
}

internal val scopeEnding: String = "}"

internal fun ensureBlankOrEscaping(text: String?, escapedLineSeparator: String): String =
    text?.lines()?.joinToString(escapedLineSeparator) ?: " "

private fun Shape.asShapeType(): String = when (this) {
    Shape.Person -> "actor"
    Shape.Component -> "component"
    Shape.Cylinder -> "database"
    Shape.Folder -> "folder"
    Shape.Ellipse, Shape.Circle -> "storage"
    else -> "rectangle"
}
