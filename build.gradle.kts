plugins {
    id("com.android.application") version com.sagrawal.newsapp.buildsrc.Versions.applicationVersion apply false
    id("com.android.library") version com.sagrawal.newsapp.buildsrc.Versions.applicationVersion apply false
    id("org.jetbrains.kotlin.android") version com.sagrawal.newsapp.buildsrc.Versions.jetBrainKotlinVersion apply false
    id("com.google.dagger.hilt.android") version com.sagrawal.newsapp.buildsrc.Versions.daggerHilt apply false
    id("com.google.devtools.ksp") version com.sagrawal.newsapp.buildsrc.Versions.ksp apply false
    id("org.jetbrains.kotlin.jvm") version com.sagrawal.newsapp.buildsrc.Versions.jetBrainJavaVersion apply false

}