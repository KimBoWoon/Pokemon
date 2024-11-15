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
}