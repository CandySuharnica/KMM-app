plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization")
    id("dev.icerock.mobile.multiplatform-resources")
}

dependencies{
    commonMainApi(libs.moko.resources.common)
}

kotlin {

    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies {
                //Network
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.cio)
                //Coroutines
                implementation(libs.coroutines)
                //Logger
                implementation(libs.napier)
                //JSON
                implementation(libs.kotlinx.serialization.core)
                // DI
                api(libs.koin.core)
                //database
                implementation(libs.sqlDelight.common)
                implementation(libs.sqlDelight.coroutines)
                //resources
                //api(libs.moko.resources.main)
            }
        }
        /*val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }*/
        val androidMain by getting{
            dependencies {
                //database
                implementation(libs.sqlDelight.android)
                //Network
                implementation(libs.ktor.android)
                //resources
                //api(libs.moko.resources.android)
            }
        }
       // val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                //database
                implementation(libs.sqlDelight.ios)
                //Network
                implementation(libs.ktor.ios)
            }
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "by.candy.suharnica" // required
}

sqldelight {
    database("CandyDatabase") { // This will be the name of the generated database class.
        packageName = "by.candy.suharnica.core.dataSource.database"
        sourceFolders = listOf("kotlin")
    }
}