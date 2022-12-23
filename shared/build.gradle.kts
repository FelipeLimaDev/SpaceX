plugins {
    kotlin(KotlinPlugins.multiplatform)
    kotlin(KotlinPlugins.cocoapods)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.androidLibrary)
//    id(Plugins.sqlDelight)
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = false
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation(Ktor.ktorCore)
                implementation(Ktor.ktorSerialization)
                implementation(Ktor.ktorSerializationJson)
                implementation(SQLDelight.sqlDelightRuntime)
            }
        }
        val commonTest by getting
        val androidMain by getting{
            dependencies{
                implementation(Ktor.ktorAndroid)
                implementation(SQLDelight.sqlDelightAndroidDriver)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Ktor.ktorIOS)
                implementation(SQLDelight.sqlDelightNativeDriver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.fdlr.spacex"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}


//
//sqldelight {
//    database("SpaceDatabase") {
//        packageName = "com.fdlr.spacex.datasource.cache"
//        sourceFolders = listOf("sqldelight")
//    }
//
//}