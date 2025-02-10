plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo"
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.material.icons.extended)
}
