val koinVersion = "3.1.4"
val napierVersion = "2.2.0"
val logbackVersion = "1.3.0-alpha12"

plugins {
    kotlin("multiplatform") version "1.6.10"
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
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.github.aakira:napier:$napierVersion")
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-cio:1.6.7")
                implementation("io.ktor:ktor-html-builder:1.6.7")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.281-kotlin-1.6.10")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-csstype:3.0.10-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:17.0.2-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.1.1-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled-next:1.0-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui:5.2.4-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons:5.2.4-pre.281-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.7.1-pre.283-kotlin-1.6.10")
                implementation(npm("@emotion/react", "11.7.1"))
                implementation(npm("@emotion/styled", "11.6.0"))
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
