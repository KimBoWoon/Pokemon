plugins {
    id("bowoon.library")
}

android {
    namespace = "com.bowoon.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}