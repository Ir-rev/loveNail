plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.mock_screen"
}

dependencies {
    implementation(project(":sources:features:calendar:calendar_api"))
    implementation(project(":sources:features:procedure:procedure_api"))
    implementation(project(":sources:features:client:clients_api"))
}