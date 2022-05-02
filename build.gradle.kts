buildscript {
    val compose_version by extra("1.0.1")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

    }
    dependencies {
        classpath(libs.kotlin)
        classpath(libs.gradle)
        classpath (libs.google.services)
        classpath(libs.sqlDelight.gradle)
        classpath(libs.kotlinx.serialization.kotlin.v)
        classpath(libs.moko.resources)
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
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