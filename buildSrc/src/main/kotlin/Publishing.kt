import org.gradle.api.Project

private const val majorVersion: Int = 0
private const val minorVersion: Int = 0
private val patchVersion = 4

val Project.publishingGroupId: String
    get() = "co.uzzu.structurizr.ktx"

val Project.publishingArtifactIdBase: String
    get() = project.name

val Project.publishingArtifactVersion: String
    get() = "$majorVersion.$minorVersion.$patchVersion"

object MavenPublications {
    const val description = "Structurizr kotlin extensions"
    const val url = "https://github.com/uzzu/structurizr-ktx"
    const val license = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val licenseDistribution = "repo"
    const val developersId = "uzzu"
    const val developersName = "Hirokazu Uzu"
    const val organization = developersId
    const val organizationUrl = "https://uzzu.co"
    const val scmUrl = "https://github.com/uzzu/structurizr-ktx"
}

val Project.bintrayUser: String?
    get() = findProperty("bintrayUser") as String?
val Project.bintrayApiKey: String?
    get() = findProperty("bintrayApiKey") as String?

object Bintray {
    const val mavenUrl = "https://dl.bintray.com/uzzu/maven"
    const val repo = "maven"
    const val packageName = "structurizr-ktx"
    const val desc = MavenPublications.description
    const val userOrg = MavenPublications.organization
    const val websiteUrl = MavenPublications.url
    const val issueTrackerUrl = "https://github.com/uzzu/structurizr-ktx/issues"
    const val vcsUrl = "https://github.com/uzzu/structurizr-ktx"
    const val githubRepo = "uzzu/structurizr-ktx"
    const val githubReleaseNoteFile = "CHANGELOG.md"
    val licenses = arrayOf("Apache-2.0")
    val labels = arrayOf("Kotlin")
    val publicDownloadNumbers = true
}
