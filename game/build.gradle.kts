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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.29")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.2.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.2.1")
    testImplementation("io.mockk:mockk:1.12.3")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}