plugins {
    alias(libs.plugins.multiplatform) apply false
}

allprojects {
    group = "digital.guimauve.pkg"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}
