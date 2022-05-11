import java.net.URI

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin)
        classpath(libs.gradle)
        classpath(libs.google.services)
        classpath(libs.sqlDelight.gradle)
        classpath(libs.kotlinx.serialization.kotlin.v)
        classpath(libs.moko.resources)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}