package com.sagrawal.newsapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TopHeadlineActivityContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsSourcesActivityContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl