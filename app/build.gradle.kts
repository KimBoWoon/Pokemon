plugins {
    id("bowoon.application")
    id("bowoon.application.compose")
    id("bowoon.hilt")
}

dependencies {
    arrayOf(
        libs.androidx.lifecycle.runtime.ktx,
        libs.retrofit2,
        platform(libs.okhttp.bom),
        libs.okhttp.okhttp,
        libs.okhttp.profiler,
        libs.okhttp.logging,
        libs.kotlin.serialization.converter,
        libs.kotlin.serialization,
        libs.compose.navigation,
        libs.compose.hilt.navigation,
        libs.compose.paging,
        libs.coil,
        libs.coil.compose,
        libs.coil.okhttp,
        libs.coil.core,
    ).forEach {
        implementation(it)
    }
}