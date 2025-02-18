plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
    alias(libs.plugins.ijustwantsomethingtodo.android.navigation)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo"
}
dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain.activethingtodo)
    implementation(projects.ui.thingtodo)
    implementation(projects.ui.common)
    implementation(projects.core.time)
    implementation(libs.androidx.adaptive.navigation.android)
}
