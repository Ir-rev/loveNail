plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.week_calendar_screen"
}

dependencies {
    implementation(project(":sources:features:calendar:calendar_api"))
}