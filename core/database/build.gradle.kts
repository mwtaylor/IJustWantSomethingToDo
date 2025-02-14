plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.library)
    alias(libs.plugins.ijustwantsomethingtodo.room)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo.core.database"
}

dependencies {
    annotationProcessor(libs.androidx.room.compiler)
    implementation(projects.core.data)
    api(projects.core.domain.common)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kotlin.test)
}
