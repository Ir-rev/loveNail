plugins {
    id("feature-setup")
}

android {
    namespace = "ru.pervov.lovenail.calendar_domain"
}

dependencies {

    api(project(":sources:features:calendar:calendar_api"))
    api(project(":sources:features:procedure:procedure_api"))
    api(project(":sources:features:client:clients_api"))
    implementation(project(":sources:features:utils:date_utils"))
}