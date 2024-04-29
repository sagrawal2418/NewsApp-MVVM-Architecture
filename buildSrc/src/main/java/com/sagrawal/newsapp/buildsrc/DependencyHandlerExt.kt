package com.sagrawal.newsapp.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependency: Any) {
    addDependency("implementation", dependency)
}

internal fun DependencyHandler.test(dependency: Any) {
    addDependency("test", dependency)
}

internal fun DependencyHandler.testImplementation(dependency: Any) {
    addDependency("testImplementation", dependency)
}

internal fun DependencyHandler.androidTestImplementation(dependency: Any) {
    addDependency("androidTestImplementation", dependency)
}

internal fun DependencyHandler.debugImplementation(dependency: Any) {
    addDependency("debugImplementation", dependency)
}

internal fun DependencyHandler.kapt(dependency: Any) {
    addDependency("kapt", dependency)
}

internal fun DependencyHandler.ksp(dependency: Any) {
    addDependency("ksp", dependency)
}

private fun DependencyHandler.addDependency(tag: String, dependency: Any) {
    if (dependency is Collection<*>) {
        dependency.forEach {
            add(tag, it!!)
        }
    } else {
        add(tag, dependency)
    }
}