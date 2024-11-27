plugins {
    id("bowoon.library")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.network"
}

dependencies {
    arrayOf(
        libs.kotlinx.serialization.converter,
        libs.kotlinx.serialization.json,
        libs.retrofit2,
        platform(libs.okhttp.bom),
        libs.okhttp.okhttp,
        libs.okhttp.profiler,
        libs.okhttp.logging
    ).forEach {
        implementation(it)
    }

    arrayOf(
        projects.core.common,
        projects.core.model
    ).forEach {
        api(it)
    }

    testImplementation(projects.core.testing)
    testImplementation(libs.retrofit2)
    testImplementation(libs.kotlinx.serialization.converter)
    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockwebserver)
}