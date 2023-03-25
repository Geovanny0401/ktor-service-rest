val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.google.devtools.ksp") version "1.8.0-1.0.8"
}

group = "com.geovannycode"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("io.insert-koin:koin-ktor:3.3.1")
    implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
    implementation("io.insert-koin:koin-annotations:1.1.1")
    implementation("com.ToxicBakery.library.bcrypt:bcrypt:1.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("io.github.smiley4:ktor-swagger-ui:1.0.2")
    implementation("io.github.reactivecircus.cache4k:cache4k:0.9.0")

    ksp("io.insert-koin:koin-ksp-compiler:1.1.1")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
