plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
    alias(libs.plugins.ijustwantsomethingtodo.android.navigation)
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.ui.thingtodo)
    implementation(projects.core.domain.activethingtodo)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.screens.welcome"
}
