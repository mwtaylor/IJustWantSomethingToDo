plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.core.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
