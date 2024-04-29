import com.sagrawal.newsapp.buildsrc.ProjectConfig
import com.sagrawal.newsapp.buildsrc.compose
import com.sagrawal.newsapp.buildsrc.composeBom
import com.sagrawal.newsapp.buildsrc.daggerHilt
import com.sagrawal.newsapp.buildsrc.hiltWorkManager
import com.sagrawal.newsapp.buildsrc.workManager

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sagrawal.newsapp.sync"
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
    implementation(project(":presentation"))

    composeBom()
    compose()
    workManager()
    hiltWorkManager()
    daggerHilt()

}