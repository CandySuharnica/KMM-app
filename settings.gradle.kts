pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "CandySuharnica"
include(":androidApp")
include(":shared")

enableFeaturePreview("VERSION_CATALOGS")