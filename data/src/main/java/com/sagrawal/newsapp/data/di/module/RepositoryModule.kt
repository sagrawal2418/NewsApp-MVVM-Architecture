package com.sagrawal.newsapp.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.local.AppDatabaseService
import com.sagrawal.newsapp.data.repository.CountriesRepositoryImpl
import com.sagrawal.newsapp.data.repository.LanguagesRepositoryImpl
import com.sagrawal.newsapp.data.repository.NewsSourcesRepositoryImpl
import com.sagrawal.newsapp.data.repository.SearchSourcesRepositoryImpl
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import com.sagrawal.newsapp.data.repository.TopHeadlineRepositoryImpl
import com.sagrawal.newsapp.domain.repository.CountriesRepository
import com.sagrawal.newsapp.domain.repository.LanguagesRepository
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTopHeadlineRepository(
        databaseService: AppDatabaseService,
        networkService: NetworkService
    ): TopHeadlineRepository {
        return TopHeadlineRepositoryImpl(databaseService, networkService)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(): CountriesRepository {
        return CountriesRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLanguagesRepository(): LanguagesRepository {
        return LanguagesRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideNewsSourcesRepository(networkService: NetworkService): NewsSourcesRepository {
        return NewsSourcesRepositoryImpl(networkService)
    }

    @Provides
    @Singleton
    fun provideSearchSourcesRepository(
        networkService: NetworkService
    ): SearchSourcesRepository {
        return SearchSourcesRepositoryImpl(networkService)
    }

}