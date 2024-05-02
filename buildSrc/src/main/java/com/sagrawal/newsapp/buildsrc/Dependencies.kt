package com.sagrawal.newsapp.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal object Dependencies {


    // core
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitInterceptor}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val navigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigationCompose}"

    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    val composeLibraries = listOf<Any>(
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-graphics",
        "androidx.compose.ui:ui-tooling-preview",
        "androidx.compose.material3:material3"
    )

    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"

    const val browser = "androidx.browser:browser:${Versions.browser}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // viewmodel
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifeCycleViewModel}"
    const val lifeCycleRunTime =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifeCycleViewModel}"

    // dagger-hilt
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt}"
    const val androidHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"

    // room-db
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"

    //paging
    const val paging = "androidx.paging:paging-common-ktx:${Versions.paging}"
    const val pagingCommon = "androidx.paging:paging-common-ktx:${Versions.paging}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.paging}"

    //work manager
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val hiltWorkManager = "androidx.hilt:hilt-work:${Versions.hilt}"

    //test libs
    const val junit = "junit:junit:${Versions.junit}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"


    //    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoCore}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"


    const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val uiTest = "androidx.compose.ui:ui-test-junit4"
    val debugUiTest =
        listOf("androidx.compose.ui:ui-tooling", "androidx.compose.ui:ui-test-manifest")
}

fun DependencyHandler.multiDex() {
    implementation(Dependencies.multiDex)
}

fun DependencyHandler.kotlin() {
    implementation(Dependencies.coreKtx)
}

fun DependencyHandler.composeBom() {
    implementation(platform(Dependencies.composeBom))
}

fun DependencyHandler.compose() {
    composeBom()
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeLibraries)
}

fun DependencyHandler.coilCompose() {
    implementation(Dependencies.coilCompose)
}

fun DependencyHandler.navigationCompose() {
    implementation(Dependencies.navigationCompose)
}

fun DependencyHandler.viewModelLifecycle() {
    implementation(Dependencies.lifeCycleViewModel)
    implementation(Dependencies.lifeCycleRunTime)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.retrofitInterceptor)
}

fun DependencyHandler.browser() {
    implementation(Dependencies.browser)
}

fun DependencyHandler.workManager() {
    implementation(Dependencies.workManager)
}

fun DependencyHandler.hiltWorkManager() {
    implementation(Dependencies.hiltWorkManager)
    kapt(Dependencies.androidHiltCompiler)
}

fun DependencyHandler.gson() {
    implementation(Dependencies.gson)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)
}

fun DependencyHandler.daggerHiltNavigation() {
    implementation(Dependencies.hiltNavigation)
}

fun DependencyHandler.localUnitTesting() {
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.turbine)
}

fun DependencyHandler.instrumentalTesting() {
    androidTestImplementation(Dependencies.extJUnit)
    androidTestImplementation(Dependencies.espressoCore)

    composeBom()
    androidTestImplementation(platform(Dependencies.composeBom))

    androidTestImplementation(Dependencies.uiTest)
    debugImplementation(Dependencies.debugUiTest)
}

fun DependencyHandler.mockitoTest() {
    // mockito
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)
}

fun DependencyHandler.roomDB() {
    ksp(Dependencies.roomCompiler)
    implementation(Dependencies.room)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.paging)
    implementation(Dependencies.pagingCommon)
}

fun DependencyHandler.pagingCompose() {
    implementation(Dependencies.pagingCompose)
}

fun DependencyHandler.addModule(moduleName: String) {
    implementation(project(":$moduleName"))
}