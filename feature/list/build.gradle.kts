plugins {
    id("bowoon.library")
    id("bowoon.library.compose")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.list"
}

dependencies {
    arrayOf(
        projects.core.common,
        projects.core.data,
        projects.core.domain,
        projects.core.imgLoader,
        libs.compose.navigation,
        libs.compose.hilt.navigation,
        libs.compose.paging,
        libs.kotlin.serialization,
        libs.coil.compose
    ).forEach {
        implementation(it)
    }
}