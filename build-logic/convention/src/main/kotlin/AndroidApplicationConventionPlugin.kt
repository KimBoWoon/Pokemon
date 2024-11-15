import com.android.build.api.dsl.ApplicationExtension
import com.bowoon.convention.Config
import com.bowoon.convention.Config.getProp
import com.bowoon.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.text.SimpleDateFormat
import java.util.Locale

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.parcelize")
//                apply("androidx.navigation.safeargs.kotlin")
//                apply("com.google.firebase.firebase-perf")
//                apply("com.google.firebase.crashlytics")
//                apply("com.google.gms.google-services")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    compileSdk = Config.Application.Pokemon.compileSdkVersion
                    minSdk = Config.Application.Pokemon.minSdkVersion
                    extensions.configure<ApplicationExtension> {
                        namespace = Config.Application.Pokemon.applicationId
                        applicationId = Config.Application.Pokemon.applicationId
                        targetSdk = Config.Application.Pokemon.targetSdkVersion
                        versionName = Config.Application.Pokemon.versionName
                        versionCode = Config.Application.Pokemon.versionCode
                        testInstrumentationRunner = Config.ApplicationSetting.testInstrumentationRunner

                        signingConfigs {
                            register(Config.Application.Pokemon.Sign.Release.name) {
                                storeFile = file(getProp(Config.Application.Pokemon.Sign.Release.storeFile))
                                storePassword = getProp(Config.Application.Pokemon.Sign.Release.storePassword)
                                keyAlias = getProp(Config.Application.Pokemon.Sign.Release.keyAlias)
                                keyPassword = getProp(Config.Application.Pokemon.Sign.Release.keyPassword)
                            }
                            register(Config.Application.Pokemon.Sign.Debug.name) {
                                storeFile = file(getProp(Config.Application.Pokemon.Sign.Debug.storeFile))
                                storePassword = getProp(Config.Application.Pokemon.Sign.Debug.storePassword)
                                keyAlias = getProp(Config.Application.Pokemon.Sign.Debug.keyAlias)
                                keyPassword = getProp(Config.Application.Pokemon.Sign.Debug.keyPassword)
                            }
                        }
                    }

                    SimpleDateFormat(Config.ApplicationSetting.dateFormat, Locale.getDefault()).run {
                        setProperty("archivesBaseName", "${Config.Application.Pokemon.appName}-v${versionName}-${format(System.currentTimeMillis())}")
                    }
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles(
                            getDefaultProguardFile(Config.ApplicationSetting.defaultProguardFile),
                            Config.ApplicationSetting.proguardFile
                        )
                        buildConfigField("Boolean", "IS_DEBUGGING_LOGGING", "false")
                        signingConfig = signingConfigs.getByName(Config.Application.Pokemon.Sign.Release.name)
                    }
                    debug {
                        isMinifyEnabled = false
//                        applicationIdSuffix = ".dev"
                        buildConfigField("Boolean", "IS_DEBUGGING_LOGGING", "true")
                        signingConfig = signingConfigs.getByName(Config.Application.Pokemon.Sign.Debug.name)
                    }
                }

                configureKotlinAndroid(this)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(libs.findLibrary("androidx.appcompat").get())
//                "implementation"(libs.findLibrary("material").get())
//                "implementation"(libs.findLibrary("androidx.constraintlayout").get())
//                "implementation"(libs.findLibrary("firebase.performance").get())
//                "implementation"(libs.findLibrary("firebase.analytics").get())
//                "implementation"(libs.findLibrary("firebase.crashlytics").get())
//                "implementation"(platform(libs.findLibrary("firebase.bom").get()))
                "testImplementation"(libs.findLibrary("junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
            }
        }
    }
}