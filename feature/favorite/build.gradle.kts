plugins {
    id("bowoon.library")
    id("bowoon.library.compose")
    id("bowoon.hilt")
    id("bowoon.android.feature")
}

android {
    namespace = "com.bowoon.favorite"
}

dependencies {
    arrayOf(
        projects.core.common,
        projects.core.data,
        projects.core.domain,
        libs.androidx.navigation.compose,
        libs.androidx.compose.hilt.navigation
    ).forEach {
        implementation(it)
    }
}