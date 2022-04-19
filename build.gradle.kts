buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin)
        classpath(libs.gradle)
        classpath(libs.sqlDelight.gradle)
        classpath(libs.kotlinx.serialization.kotlin.v)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}