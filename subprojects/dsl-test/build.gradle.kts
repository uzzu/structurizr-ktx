plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(project(":dsl"))
    testImplementation("com.structurizr:structurizr-core:1.6.1")
    testImplementation("com.structurizr:structurizr-plantuml:1.5.0")
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
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
