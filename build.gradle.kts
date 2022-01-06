/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Jose Salgado
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

val koinVersion = "3.1.4"
val napierVersion = "2.2.0"
val logbackVersion = "1.3.0-alpha12"
val serializationVersion = "1.3.2"
val ktorVersion = "1.6.7"
val exposedVersion = "0.37.2"
val flywayVersion = "8.3.0"
val jellyfinSDKVersion = "1.1.2"

plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

group = "com.jassycliq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        binaries.executable()
        useCommonJs()
        browser {
            webpackTask {
                output.libraryTarget = "commonjs2"
            }
            commonWebpackConfig {
                sourceMaps = true
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.github.aakira:napier:$napierVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-auth:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("io.insert-koin:koin-test:$koinVersion")
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-cio:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-locations:$ktorVersion")
                implementation("io.ktor:ktor-auth:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.281-kotlin-1.6.10")
                implementation("io.insert-koin:koin-ktor:$koinVersion")
                implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")

                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
                implementation("org.flywaydb:flyway-core:$flywayVersion")
                implementation("com.zaxxer:HikariCP:5.0.0")
                implementation("com.h2database:h2:2.0.204")

                implementation("org.jellyfin.sdk:jellyfin-core:$jellyfinSDKVersion")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-csstype:3.0.10-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:17.0.2-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.2.1-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled-next:1.0-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui:5.2.7-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons:5.2.5-pre.286-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.7.1-pre.286-kotlin-1.6.10")
                implementation(npm("@emotion/react", "11.7.1"))
                implementation(npm("@emotion/styled", "11.6.0"))
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("com.jassycliq.application.ServerKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion = "16.0.0"
}

tasks{
    shadowJar {
        archiveClassifier.set("")
        archiveVersion.set("")
        archiveAppendix.set("")
        manifest {
            attributes(Pair("Main-Class", "com.jassycliq.application.ServerKt"))
        }
    }
}
