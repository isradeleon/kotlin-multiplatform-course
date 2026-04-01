import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    // KSP plugin is applied for Android Room annotation processing
    alias(libs.plugins.ksp)
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
            implementation(libs.composeUiToolingPreview)
            implementation(libs.androidxActivityCompose)
            // Android-specific dependencies
            implementation(libs.coilCompose)
            implementation(libs.coilKtor)
            implementation(libs.ktorClientOkhttp)
            implementation(libs.koinAndroid)
            implementation(libs.koinCompose)
        }
        iosMain.dependencies {
            implementation(libs.ktorClientDarwin)
        }
        commonMain.dependencies {
            implementation(libs.composeRuntime)
            implementation(libs.composeFoundation)
            implementation(libs.composeMaterial3)
            implementation(libs.composeUi)
            implementation(libs.composeComponentsResources)
            implementation(libs.composeUiToolingPreview)
            implementation(libs.androidxLifecycleViewModelCompose)
            implementation(libs.androidxLifecycleRuntimeCompose)
            // Common libraries
            implementation(libs.ktorClientCore)
            implementation(libs.ktorClientContentNegotiation)
            implementation(libs.ktorSerializationKotlinx)
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.kotlinxDatetime)
            implementation(libs.koinCore)
            implementation(libs.koinNavigation)
            implementation(libs.koinComposeViewModel)
            implementation(libs.composeMaterialIconsExtended)
            implementation(libs.roomRuntime)
            implementation(libs.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlinTest)
        }
    }
}

android {
    namespace = "com.isradeleon.ainews"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.isradeleon.ainews"
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

// Room schema directory
room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    debugImplementation(libs.composeUiTooling)
    // KSP processor for Room (Android)
    ksp(libs.roomCompiler)
    // also register for android-specific KSP configuration
    add("kspAndroid", libs.roomCompiler)
}

