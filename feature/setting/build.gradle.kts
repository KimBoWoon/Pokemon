plugins {
    id("bowoon.library")
    id("bowoon.library.compose")
    id("bowoon.hilt")
    id("bowoon.android.feature")
}

android {
    namespace = "com.bowoon.setting"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.hilt.navigation)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    testImplementation(projects.core.testing)
}