pluginManagement {
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

rootProject.name = "loveNail"
include(":app")

// client
include(":sources:features:clients_api")
include(":sources:features:client_list_screen")
include(":sources:features:client_create_screen")

// calendar
include(":sources:features:week_calendar_screen")

// contact reader
include(":sources:features:contact_reader")

// utils
include(":sources:features:permissions_getter")
include(":sources:features:navigation")
include(":sources:features:utils")
