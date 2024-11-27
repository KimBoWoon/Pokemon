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

rootProject.name = "Pokemon"
include(":app")
include(":core:data")
include(":core:network")
include(":core:common")
include(":core:domain")
include(":feature:list")
include(":feature:pokemon")
include(":feature:favorite")
include(":core:datastore")
include(":core:model")
include(":feature:setting")
include(":core:ui")
include(":core:testing")