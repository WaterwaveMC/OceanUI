plugins {
    id("java")
    kotlin("jvm")
}

group = "dev.yolocat.oceanui"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(11)
}