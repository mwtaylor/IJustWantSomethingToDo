import app.elephantintheroom.ijustwantsomethingtodo.build.configureKotlinAndroid
import app.elephantintheroom.ijustwantsomethingtodo.build.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target:Project){
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.minSdk = 35
                defaultConfig.targetSdk = 35
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            dependencies {
                "androidTestImplementation"(libs.findLibrary("androidx.test.runner").get())
            }
        }
    }
}
