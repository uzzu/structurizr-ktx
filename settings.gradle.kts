rootProject.name = "structurizr-ktx"

fun Settings.includeSubProject(name: String) {
    include(":$name")
    project(":$name").projectDir = File("$rootDir/subprojects/$name")
    project(":$name").buildFileName = "build.gradle.kts"
}

includeSubProject("dsl")

// endregion
