pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ijustwantsomethingtodo"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
// Sub-projects are ordered from highest-level to low-level libraries
// A sub-project can only depend on something lower than it in the order
include(":app")
include(":screens:welcome")
include(":screens:tasks")
include(":screens:planning")
include(":screens:reports")
include(":core:database")
include(":core:data")
