plugins {
    id("bowoon.library")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.network)

    implementation(libs.kotlin.serialization)
}