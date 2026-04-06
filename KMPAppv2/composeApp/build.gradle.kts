import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.internal.utils.getLocalProperty

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    // Kotlin serialization & KSP plugins
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)

    // BuildKonfig plugin to manage local API keys
    alias(libs.plugins.buildkonfig)

    // Room plugin
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            // Ktor Android
            implementation(libs.ktor.android)

            // Koin Android
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Ktor common
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor)

            // Coil for image loading
            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.core)
            implementation(libs.coil.svg)
            implementation(libs.coil.network.ktor)

            // Koin Dependency Injection
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.navigation)

            // Room & SQLite
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // Kotlin date time for multiplatform
            implementation(libs.kotlin.date.time)

            // Material icons
            implementation(libs.material.icons.extended)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            // Ktor iOS
            implementation(libs.ktor.ios)
        }
    }
}

android {
    namespace = "com.isradeleon.kmpappv2"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.isradeleon.kmpappv2"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

/**
 * Custom BuildKonfig block for simulating the
 * classic BuildConfig behavior, but now for iOS as well.
 */
buildkonfig {
    // Set the package name where the BuildKonfig object will be generated. This is required.
    packageName = "com.isradeleon.kmpappv2"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
        // Access the local.properties file
        val apiKey = project.getLocalProperty("COINRANKING_KEY") ?: ""

        // Make api key required
        require(apiKey.isNotEmpty()) {
            "Register your api key from developer and place it in local.properties as `COINRANKING_KEY`"
        }

        // Add the property to the generated BuildKonfig object
        buildConfigField(STRING, "COINRANKING_KEY", apiKey)
    }
}

// Room schema directory
room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    // Room compiler
    ksp(libs.room.compiler)
    debugImplementation(libs.compose.uiTooling)
}

