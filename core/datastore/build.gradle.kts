plugins {
    id("bowoon.library")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.datastore"
}

dependencies {
    arrayOf(
        projects.core.common,
        libs.kotlinx.serialization.json,
        libs.androidx.junit
    ).forEach {
        implementation(it)
    }

    arrayOf(
        libs.androidx.datastore,
        projects.core.model
    ).forEach {
        api(it)
    }

    testImplementation(projects.core.testing)
}