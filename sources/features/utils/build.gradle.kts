plugins {
    id("feature-setup")
}

android {
    namespace = "ru.pervov.lovenail.contact_reader"
}

dependencies {
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}