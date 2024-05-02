import com.sagrawal.newsapp.buildsrc.ProjectConfig
import com.sagrawal.newsapp.buildsrc.daggerHilt
import com.sagrawal.newsapp.buildsrc.localUnitTesting
import com.sagrawal.newsapp.buildsrc.paging
import com.sagrawal.newsapp.buildsrc.retrofit
import com.sagrawal.newsapp.buildsrc.roomDB

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {

    namespace = "com.sagrawal.newsapp.data"

    compileSdk = ProjectConfig.compileSdk
    buildToolsVersion = ProjectConfig.buildToolsVersion

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

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
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":utils"))
    implementation(project(":nativelib"))

    retrofit()
    daggerHilt()
    roomDB()
    localUnitTesting()
    paging()
}