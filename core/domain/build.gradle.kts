plugins {
    id("bowoon.library")
    id("bowoon.hilt")
}

android {
    namespace = "com.bowoon.domain"
}

dependencies {
    arrayOf(
        projects.core.data
    ).forEach {
        api(it)
    }

    arrayOf(
        projects.core.testing
    ).forEach {
        implementation(it)
    }

    implementation("androidx.paging:paging-common:3.3.4")
}