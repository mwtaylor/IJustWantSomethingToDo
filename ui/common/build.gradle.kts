plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.ui.common"
}

dependencies {
    implementation(projects.core.time)
}
