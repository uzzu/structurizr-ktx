import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    `java-library`
    id("org.jetbrains.dokka")
    `maven-publish`
    signing
}

dependencies {
    implementation("com.structurizr:structurizr-core:1.6.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn", "-Xjvm-default=all")
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn", "-Xjvm-default=all")
        }
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}
val dokkaJavadoc = tasks.getByName("dokkaJavadoc", DokkaTask::class)
val dokkaJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn(dokkaJavadoc)
    from(dokkaJavadoc.outputDirectory)
}

group = publishingGroupId
version = publishingArtifactVersion(true)
setProperty("archivesBaseName", publishingArtifactIdBase)

publishing {
    repositories {
        maven {
            url = env.PUBLISH_PRODUCTION.orNull()
                ?.run { uri("https://oss.sonatype.org/service/local/staging/deploy/maven2") }
                ?: uri("https://oss.sonatype.org/content/repositories/snapshots")
            credentials {
                username = env.OSSRH_USERNAME.orElse("")
                password = env.OSSRH_PASSWORD.orElse("")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components.getByName("java"))
            groupId = publishingGroupId
            artifactId = publishingArtifactIdBase
            version = publishingArtifactVersion(true)

            artifact(sourcesJar)
            artifact(dokkaJar)

            pom {
                name.set(publishingArtifactIdBase)
                description.set(MavenPublications.description("DSL for structurizr/java"))
                url.set(MavenPublications.url)
                licenses {
                    license {
                        name.set(MavenPublications.license)
                        url.set(MavenPublications.licenseUrl)
                        distribution.set(MavenPublications.licenseDistribution)
                    }
                }
                developers {
                    developer {
                        id.set(MavenPublications.developersId)
                        name.set(MavenPublications.developersName)
                        organization.set(MavenPublications.organization)
                        organizationUrl.set(MavenPublications.organizationUrl)
                    }
                }
                scm {
                    url.set(MavenPublications.scmUrl)
                }
            }
        }
    }
}

signing {
    if (env.PUBLISH_PRODUCTION.isPresent) {
        setRequired { gradle.taskGraph.hasTask("publish") }
        sign(publishing.publications)

        @Suppress("UnstableApiUsage")
        useInMemoryPgpKeys(
            env.SIGNING_KEYID.orElse(""),
            env.SIGNING_KEY.orElse(""),
            env.SIGNING_PASSWORD.orElse("")
        )
    }
}
