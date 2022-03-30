plugins {
    kotlin("jvm") version "1.6.10"
    id("io.ratpack.ratpack-java")
}

group = "me.kcybulski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":coordinator"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

application {
    mainClass.set("me.kcybulski.life.server.StartKt")
}
