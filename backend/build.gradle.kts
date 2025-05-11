plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktor)
}

application {
    mainClass.set("digital.guimauve.pkg.ApplicationKt")
}

ktor {
    docker {
        jreVersion.set(JavaVersion.VERSION_21)
        localImageName.set("pkg")

        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { "pkg" },
                username = provider { "guimauvedigital" },
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}

kotlin {
    jvmToolchain(21)
    jvm {
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":commons"))
                implementation(libs.bundles.kaccelero.ktor)
                implementation(libs.bundles.ktor.server.api)
                implementation(libs.bundles.ktor.client.api)
                implementation(libs.koin.core)
                implementation(libs.koin.ktor)
                implementation(libs.logback.core)
                implementation(libs.logback.classic)
                implementation(libs.mysql)
                implementation(libs.s3)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.bundles.ktor.server.tests)
                implementation(libs.tests.mockk)
                implementation(libs.tests.h2)
                implementation(libs.tests.coroutines)
            }
        }
    }
}
