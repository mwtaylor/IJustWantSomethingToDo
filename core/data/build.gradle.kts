plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.core.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
