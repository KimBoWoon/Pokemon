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
        libs.coil.compose,
        libs.coil.okhttp
    ).forEach {
        implementation(it)
    }
}