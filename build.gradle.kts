plugins {
    alias(libs.plugins.multiplatform) apply false
}

allprojects {
    group = "digital.guimauve.pkg"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}
