plugins {
    id("feature-setup")
}

android {
    namespace = "ru.pervov.lovenail.contact_reader"
}

dependencies {
    implementation(project(":sources:features:client:clients_api"))
}