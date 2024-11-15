plugins {
    id("bowoon.application")
    id("bowoon.application.compose")
    id("bowoon.hilt")
}

dependencies {
    arrayOf(
        libs.androidx.lifecycle.runtime.ktx,
        libs.compose.navigation,
        libs.coil.compose,
        projects.core.common,
        projects.core.data,
        projects.feature.list,
        projects.feature.pokemon
    ).forEach {
        implementation(it)
    }
}