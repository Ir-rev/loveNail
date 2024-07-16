plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.client_create_screen"
}

dependencies {
    implementation(project(":sources:features:clients_api"))
}