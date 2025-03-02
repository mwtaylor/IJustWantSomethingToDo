plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.ui.common)
    implementation(projects.core.time)
    implementation(libs.material.icons.extended)
}
