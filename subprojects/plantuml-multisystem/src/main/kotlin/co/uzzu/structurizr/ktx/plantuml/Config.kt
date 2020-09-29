package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.view.AutomaticLayout
import java.net.URI

open class Config(
    internal val skinParams: MutableMap<String, String> = LinkedHashMap(),
    internal val includes: MutableList<String> = mutableListOf(),
    internal var useSequenceDiagram: Boolean = false,
    internal var hideStereoType: Boolean = false,
    internal var direction: AutomaticLayout.RankDirection = AutomaticLayout.RankDirection.TopBottom,
    internal var lineSeparator: String = '\n'.toString()
) {
    internal val escapedLineSeparator: String
        get() = when (lineSeparator) {
            "\n" -> "\\n"
            "\r" -> "\\r"
            "\r\n" -> "\\r\\n"
            else -> throw IllegalStateException("Invalid line separator character: $lineSeparator")
        }
}

class ConfigScope<TConfig : Config>(internal var config: TConfig) {
    var useSequenceDiagram: Boolean
        get() = config.useSequenceDiagram
        set(value) {
            config.useSequenceDiagram = value
        }

    var hideStereoType: Boolean
        get() = config.hideStereoType
        set(value) {
            config.hideStereoType = value
        }

    var direction: AutomaticLayout.RankDirection
        get() = config.direction
        set(value) {
            config.direction = value
        }

    var lineSeparator: String
        get() = config.lineSeparator
        set(value) {
            config.lineSeparator = when (value) {
                "\n", "\r", "\r\n" -> value
                else -> throw IllegalArgumentException("Unexpected line separator characters: $value")
            }
        }

    fun skinParam(name: String, value: String) {
        config.skinParams[name] = value
    }

    fun includeFile(file: String, id: String? = null) {
        val idDeclaration = id?.let { "!$it" } ?: ""
        config.includes.add("""!include $file$idDeclaration""")
    }

    fun includeFile(file: String, id: Int) {
        includeFile(file, id.toString())
    }

    fun includeUrl(uri: URI, id: String? = null) {
        val idDeclaration = id?.let { "!$it" } ?: ""
        config.includes.add("""!include $uri$idDeclaration""")
    }

    fun includeUrl(uri: URI, id: Int) {
        includeUrl(uri, id.toString())
    }
}
