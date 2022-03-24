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
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.2.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.2.1")

}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}