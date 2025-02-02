import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "app.elephantintheroom.ijustwantsomethingtodo.build"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("ijustwantsomethingtodoAndroidApplication") {
            id = libs.plugins.ijustwantsomethingtodo.android.application.get().pluginId
            implementationClass = "AndroidApplicationPlugin"
        }
        register("ijustwantsomethingtodoAndroidCompose") {
            id = libs.plugins.ijustwantsomethingtodo.android.compose.get().pluginId
            implementationClass = "AndroidComposePlugin"
        }
        register("ijustwantsomethingtodoAndroidLibrary") {
            id = libs.plugins.ijustwantsomethingtodo.android.library.get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }
        register("ijustwantsomethingtodoRoom") {
            id = libs.plugins.ijustwantsomethingtodo.room.get().pluginId
            implementationClass = "RoomPlugin"
        }
    }
}
