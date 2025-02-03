plugins {
    alias(libs.plugins.ijustwantsomethingtodo.android.application)
    alias(libs.plugins.ijustwantsomethingtodo.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "app.elephantintheroom.ijustwantsomethingtodo"

    defaultConfig {
        applicationId = "app.elephantintheroom.ijustwantsomethingtodo"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
