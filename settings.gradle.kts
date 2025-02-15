pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.legacyfabric.net/")
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.1.10"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "OceanUI"

include("OceanUI-Test")
