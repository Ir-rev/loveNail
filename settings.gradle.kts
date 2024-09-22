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
include(":sources:features:client:clients_api")

// client screen
include(":sources:features:client:client_list_screen")
include(":sources:features:client:client_create_screen")

// calendar
include(":sources:features:calendar:calendar_api")
include(":sources:features:calendar:calendar_domain")

// calendar screen
include(":sources:features:calendar:week_calendar_screen")
include(":sources:features:calendar:day_calendar_screen")

// procedure
include(":sources:features:procedure:procedure_api")

// procedure screen
include(":sources:features:procedure:procedure_list_screen")
include(":sources:features:procedure:procedure_create_screen")

// contact reader
include(":sources:features:utils:contact_reader")

// utils
include(":sources:features:utils:permissions_getter")
include(":sources:features:utils:navigation")
include(":sources:features:utils:utils")
include(":sources:features:utils:toolbar")
include(":sources:features:utils:date_utils")

// mock
include(":sources:mock:mock_screen")
