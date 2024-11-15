import com.android.build.gradle.LibraryExtension
import com.bowoon.convention.Config
import com.bowoon.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.parcelize")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    compileSdk = Config.Library.MIN_SDK_VERSION
                    minSdk = Config.Library.COMPILE_SDK_VERSION
                    buildTypes {
                        release {
                            isMinifyEnabled = true
                            isJniDebuggable = false
                            buildConfigField("Boolean", "IS_DEBUGGING_LOGGING", "false")
                        }
                        debug {
                            isMinifyEnabled = false
                            buildConfigField("Boolean", "IS_DEBUGGING_LOGGING", "true")
                        }
                    }
                }

                configureKotlinAndroid(this)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(libs.findLibrary("androidx.appcompat").get())
                "implementation"(libs.findLibrary("material").get())
                "testImplementation"(libs.findLibrary("junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
            }
        }
    }
}