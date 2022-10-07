plugins {
    id ("com.android.application")
    id ("kotlin-android")
}

android {
	namespace = "rasel.lunar.launcher"
    compileSdk = 33

    defaultConfig {
        applicationId = "rasel.lunar.launcher"
        minSdk = 26
        targetSdk = 33
        versionCode = 21
        versionName = "2.1"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue ("string", "app_name", "Lunar Launcher Debug")
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            resValue ("string", "app_name", "Lunar Launcher")
        }
    }

    applicationVariants.all {
        if (buildType.name == "release") {
            outputs.all {
                val output = this as? com.android.build.gradle.internal.api.BaseVariantOutputImpl
                if (output?.outputFileName?.endsWith(".apk") == true) {
                    output.outputFileName = "${defaultConfig.applicationId}_v${defaultConfig.versionName}.apk"
                }
            }
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    val kotlinVersion: String? by extra

    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("androidx.biometric:biometric-ktx:1.2.0-alpha05")
    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("com.google.android.material:material:1.6.1")
    implementation (kotlin("stdlib", version = kotlinVersion))
    implementation ("dev.chrisbanes.insetter:insetter:0.6.1")
}
