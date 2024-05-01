import com.sagrawal.newsapp.buildsrc.ProjectConfig
import com.sagrawal.newsapp.buildsrc.browser
import com.sagrawal.newsapp.buildsrc.coilCompose
import com.sagrawal.newsapp.buildsrc.compose
import com.sagrawal.newsapp.buildsrc.composeBom
import com.sagrawal.newsapp.buildsrc.daggerHilt
import com.sagrawal.newsapp.buildsrc.daggerHiltNavigation
import com.sagrawal.newsapp.buildsrc.instrumentalTesting
import com.sagrawal.newsapp.buildsrc.kotlin
import com.sagrawal.newsapp.buildsrc.localUnitTesting
import com.sagrawal.newsapp.buildsrc.mockitoTest
import com.sagrawal.newsapp.buildsrc.navigationCompose
import com.sagrawal.newsapp.buildsrc.paging
import com.sagrawal.newsapp.buildsrc.pagingCompose
import com.sagrawal.newsapp.buildsrc.viewModelLifecycle

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sagrawal.newsapp.presentation"
    compileSdk = ProjectConfig.compileSdk
    buildToolsVersion = ProjectConfig.buildToolsVersion

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

        vectorDrawables {
            useSupportLibrary = true
        }

        resourceConfigurations.add("en")

        consumerProguardFiles("consumer-rules.pro")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":utils"))

    paging()
    pagingCompose()
    viewModelLifecycle()
    browser()
    kotlin()
    composeBom()
    compose()
    coilCompose()
    navigationCompose()
    daggerHilt()
    daggerHiltNavigation()
    instrumentalTesting()
    localUnitTesting()
    mockitoTest()
}