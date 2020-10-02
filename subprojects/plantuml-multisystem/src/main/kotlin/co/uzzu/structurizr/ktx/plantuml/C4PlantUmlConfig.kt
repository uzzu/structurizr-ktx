package co.uzzu.structurizr.ktx.plantuml

import java.net.URI

class C4PlantUmlConfig
internal constructor(
    internal var definitionRoot: C4DefinitionRoot = C4DefinitionRoot.PlantUml,
    internal var layoutWithLegend: Boolean = true,
) : Config()

var ConfigScope<C4PlantUmlConfig>.definitionRoot: C4DefinitionRoot
    get() = config.definitionRoot
    set(value) {
        config.definitionRoot = value
    }

var ConfigScope<C4PlantUmlConfig>.layoutWithLegend: Boolean
    get() = config.layoutWithLegend
    set(value) {
        config.layoutWithLegend = value
    }

sealed class C4DefinitionRoot {

    object PlantUml : C4DefinitionRoot() {
        fun values(): List<String> = listOf(
            "<C4/C4>",
            "<C4/C4_Context>",
            "<C4/C4_Container>",
            "<C4/C4_Component>",
        )
    }

    data class PathDefined(private val path: String) : C4DefinitionRoot() {
        fun values(): List<String> = listOf(
            "$path/C4.puml",
            "$path/C4_Context.puml",
            "$path/C4_Container.puml",
            "$path/C4_Component.puml",
        )
    }

    data class UrlDefined(private val baseUrl: String) : C4DefinitionRoot() {
        init {
            URI(baseUrl)
        }

        fun values(): List<URI> = listOf(
            URI("$baseUrl/C4.puml").normalize(),
            URI("$baseUrl/C4_Context.puml").normalize(),
            URI("$baseUrl/C4_Container.puml").normalize(),
            URI("$baseUrl/C4_Component.puml").normalize()
        )
    }

    @Suppress("FunctionName")
    fun GitHub(orgSlashRepo: String, commit: String = "master"): UrlDefined =
        UrlDefined("https://raw.githubusercontent.com/$orgSlashRepo/$commit")
}
