plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail.procedure_list_screen"
}

dependencies {
    implementation(project(":sources:features:procedure_api"))

    implementation(libs.glide)
}