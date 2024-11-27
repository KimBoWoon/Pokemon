plugins {
    id("bowoon.library")
    id("bowoon.library.compose")
    id("bowoon.hilt")
    id("bowoon.android.feature")
}

android {
    namespace = "com.bowoon.list"
}

dependencies {
    arrayOf(
        projects.core.common,
        projects.core.data,
        projects.core.domain,
        projects.core.datastore,
        libs.androidx.navigation.compose,
        libs.androidx.compose.hilt.navigation,
        libs.androidx.compose.paging
    ).forEach {
        implementation(it)
    }
}