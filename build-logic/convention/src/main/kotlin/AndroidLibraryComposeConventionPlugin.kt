import com.android.build.gradle.LibraryExtension
import com.bowoon.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "implementation"(libs.findLibrary("androidx.activity.compose").get())
                "implementation"(libs.findLibrary("androidx.ui").get())
                "implementation"(libs.findLibrary("androidx.ui.graphics").get())
                "implementation"(libs.findLibrary("androidx.ui.tooling.preview").get())
                "implementation"(libs.findLibrary("androidx.material3").get())
                "androidTestImplementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "androidTestImplementation"(libs.findLibrary("androidx.ui.test.junit4").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.tooling").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.test.manifest").get())
            }
        }
    }
}