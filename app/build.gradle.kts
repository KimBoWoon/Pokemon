plugins {
    id("bowoon.application")
    id("bowoon.application.compose")
    id("bowoon.hilt")
}

dependencies {
    arrayOf(
        libs.androidx.lifecycle.runtime.ktx,
        libs.androidx.navigation.compose,
        libs.coil.compose,
        libs.androidx.compose.material3.navigationSuite,
        projects.core.common,
        projects.core.data,
        projects.core.ui,
        projects.feature.list,
        projects.feature.pokemon,
        projects.feature.favorite,
        projects.feature.setting
    ).forEach {
        implementation(it)
    }
}