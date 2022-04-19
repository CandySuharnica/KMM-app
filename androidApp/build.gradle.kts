plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "by.candy.suharnica.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.material)
    implementation(libs.appcompat)
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)
}