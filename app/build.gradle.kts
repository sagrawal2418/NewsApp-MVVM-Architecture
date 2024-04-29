import com.sagrawal.newsapp.buildsrc.ProjectConfig
import com.sagrawal.newsapp.buildsrc.addModule
import com.sagrawal.newsapp.buildsrc.daggerHilt
import com.sagrawal.newsapp.buildsrc.hiltWorkManager
import com.sagrawal.newsapp.buildsrc.multiDex
import com.sagrawal.newsapp.buildsrc.workManager

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = ProjectConfig.appID
    testNamespace = ProjectConfig.appTestID

    compileSdk = ProjectConfig.compileSdk
    buildToolsVersion = ProjectConfig.buildToolsVersion

    defaultConfig {
        applicationId = ProjectConfig.appID

        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isJniDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

dependencies {

    addModule("presentation")
    addModule("utils")
    addModule("sync")
    multiDex()
    daggerHilt()
    workManager()
    hiltWorkManager()
}