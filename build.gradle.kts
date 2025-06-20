plugins {
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.maven) apply false
}

allprojects {
    group = "digital.guimauve.pkg"
    version = "0.1.3"

    repositories {
        mavenCentral()
    }
}
