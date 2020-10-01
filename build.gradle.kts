import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("co.uzzu.dotenv.gradle") version "1.1.0"
    kotlin("jvm") version "1.4.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
    id("org.jetbrains.dokka") version "1.4.0" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
        ignoreFailures.set(true)
    }
}
