plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    compileSdk = 32
    defaultConfig {
        applicationId = "by.candy.suharnica.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.appcompat)
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)
    implementation(libs.compose.material)
    implementation(libs.coil)
    implementation(platform("com.google.firebase:firebase-bom:29.3.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.23.0")
    implementation("com.itextpdf:itextg:5.5.10")
}