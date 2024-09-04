plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.pervov.lovenail"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.pervov.lovenail"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // client
    implementation(project(":sources:features:client:client_list_screen"))
    implementation(project(":sources:features:client:client_create_screen"))
    implementation(project(":sources:features:client:clients_api"))

    // calendar
    implementation(project(":sources:features:calendar:week_calendar_screen"))
    implementation(project(":sources:features:calendar:day_calendar_screen"))

    // procedure
    implementation(project(":sources:features:procedure:procedure_list_screen"))
    implementation(project(":sources:features:procedure:procedure_create_screen"))
    implementation(project(":sources:features:procedure:procedure_api"))

    // utils
    implementation(project(":sources:features:utils:navigation"))
    implementation(project(":sources:features:utils:utils"))
    implementation(project(":sources:features:calendar:calendar_api"))

    // mock
    implementation(project(":sources:mock:mock_screen"))

}