plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo"
}

dependencies {
    implementation(projects.core.domain.common)
    implementation(projects.core.data)
    implementation(libs.kotlinx.coroutines.core)
}
