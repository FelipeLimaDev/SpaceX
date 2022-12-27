
plugins {
    id(Plugins.androidApplication)
    id(Plugins.ksp) version Plugins.ksp_version
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

android {
    namespace = Application.appId
    compileSdk = Application.compileSdk
    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin{
    sourceSets{
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Compose.composeUi)
    implementation(Compose.composeUiTooling)
    implementation(Compose.composeUiToolingPreview)
    implementation(Compose.composeFoundation)
    implementation(Compose.composeMaterial)
    implementation(Compose.activityCompose)
    implementation(Compose.composeIconsExtended)
    implementation(Accompanist.coilCompose)
    implementation(Accompanist.systemuicontroller)

    implementation(Koin.koinCompose)

    implementation(Navigator.navigator)
    ksp(Navigator.ksp)

    implementation(Ktor.ktorAndroid)
    implementation(Ktor.ktorSerialization)
    implementation(Ktor.ktorSerializationJson)

    implementation(Lottie.lottieCompose)
    implementation(Accompanist.swipeRefresh)
}