plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.day_calendar_screen"
}

dependencies {

    implementation(project(":sources:features:calendar:calendar_domain"))
    implementation(libs.glide)
}