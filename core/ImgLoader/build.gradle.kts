plugins {
    id("bowoon.library")
    id("bowoon.library.compose")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.imgloader"
}

dependencies {
    arrayOf(
        projects.core.common,
        libs.coil,
        libs.coil.compose
    ).forEach {
        implementation(it)
    }

    arrayOf(
        libs.coil.okhttp
    ).forEach {
        api(it)
    }
}