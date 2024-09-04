plugins {
    id("feature-setup")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "ru.pervov.lovenail.clients_api"
}

dependencies {
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.gson)
}