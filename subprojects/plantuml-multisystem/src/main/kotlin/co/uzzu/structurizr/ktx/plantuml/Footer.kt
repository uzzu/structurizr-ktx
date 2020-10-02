package co.uzzu.structurizr.ktx.plantuml

open class Footer(
    private val config: Config
) {
    open fun asText(): String = buildString {
        @Suppress("SpellCheckingInspection")
        append("@enduml${config.lineSeparator}")
    }
}
