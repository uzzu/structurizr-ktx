package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.view.View

open class Header(
    protected val view: View,
    private val config: Config
) {
    protected val viewKey: String = view.key
    protected val title: String = view.title ?: view.name ?: ""
    protected val description: String = view.description ?: ""

    private inline val lineSeparator: String
        get() = config.lineSeparator

    open fun asText(): String = buildString {
        @Suppress("SpellCheckingInspection")
        append("@startuml(id=${viewKey.replace(" ", "_")})")
        lineSeparator()

        config.includes.forEach { append("$it$lineSeparator") }

        title.takeIf { it.isNotBlank() }
            ?.let { append("title $title$lineSeparator") }

        description.takeIf { it.isNotBlank() }
            ?.let { append("caption $it$lineSeparator") }

        append(lineSeparator)

        config.skinParams.takeIf { it.isNotEmpty() }
            ?.let {
                @Suppress("SpellCheckingInspection")
                append("skinparam {$lineSeparator")
                it.keys.forEach { name ->
                    append("  $name ${it[name]}$lineSeparator")
                }
                append("}$lineSeparator")
            }
        if (config.hideStereoType) {
            append("hide stereotype$lineSeparator")
        }
    }

    private fun StringBuilder.lineSeparator() {
        append(config.lineSeparator)
    }
}
