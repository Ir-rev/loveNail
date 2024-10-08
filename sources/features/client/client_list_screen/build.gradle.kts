plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.client_list_screen"
}

dependencies {
    implementation(project(":sources:features:client:clients_api"))

    implementation(libs.glide)
}