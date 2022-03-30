plugins {
    kotlin("jvm") version "1.6.10"
}

group = "me.kcybulski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    api(project(":game"))
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.29")
}
