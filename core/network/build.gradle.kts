plugins {
    id("bowoon.library")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.network"
}

dependencies {
    arrayOf(
        libs.kotlin.serialization.converter,
        libs.kotlin.serialization,
        libs.retrofit2,
        platform(libs.okhttp.bom),
        libs.okhttp.okhttp,
        libs.okhttp.profiler,
        libs.okhttp.logging,
    ).forEach {
        implementation(it)
    }

    arrayOf(
        projects.core.common
    ).forEach {
        api(it)
    }
}