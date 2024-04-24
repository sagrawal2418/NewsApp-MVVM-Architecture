package com.sagrawal.newsapp.domain.di.module

import com.sagrawal.newsapp.domain.repository.CountriesRepository
import com.sagrawal.newsapp.domain.repository.LanguagesRepository
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import com.sagrawal.newsapp.domain.usecase.GetCountriesUseCase
import com.sagrawal.newsapp.domain.usecase.GetLanguagesUseCase
import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesByQueriesUseCase
import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.GetOfflineTopHeadlineUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.GetPagingTopHeadlineUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNetworkTopHeadlineUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNewsByLanguageUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNewsBySourceUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.NetworkTopHeadlineUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countriesRepository: CountriesRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countriesRepository)
    }

    @Provides
    @Singleton
    fun provideGetLanguagesUseCase(languagesRepository: LanguagesRepository): GetLanguagesUseCase {
        return GetLanguagesUseCase(languagesRepository)
    }

    @Provides
    @Singleton
    fun provideGetNewsSourcesByQueriesUseCase(searchSourcesRepository: SearchSourcesRepository): GetNewsSourcesByQueriesUseCase {
        return GetNewsSourcesByQueriesUseCase(searchSourcesRepository)
    }

    @Provides
    @Singleton
    fun provideGetNewsSourcesUseCase(newsSourcesRepository: NewsSourcesRepository): GetNewsSourcesUseCase {
        return GetNewsSourcesUseCase(newsSourcesRepository)
    }

    @Provides
    @Singleton
    fun provideGetNetworkTopHeadlineUseCase(topHeadlineRepository: TopHeadlineRepository): GetNetworkTopHeadlineUseCase {
        return GetNetworkTopHeadlineUseCase(topHeadlineRepository)
    }

    @Provides
    @Singleton
    fun provideOfflineTopHeadlineUseCase(topHeadlineRepository: TopHeadlineRepository): GetOfflineTopHeadlineUseCase {
        return GetOfflineTopHeadlineUseCase(topHeadlineRepository)
    }

    @Provides
    @Singleton
    fun providePagingTopHeadlineUseCase(topHeadlineRepository: TopHeadlineRepository): GetPagingTopHeadlineUseCase {
        return GetPagingTopHeadlineUseCase(topHeadlineRepository)
    }

    @Provides
    @Singleton
    fun provideGetNewsBySourceUseCase(topHeadlineRepository: TopHeadlineRepository): GetNewsBySourceUseCase {
        return GetNewsBySourceUseCase(topHeadlineRepository)
    }

    @Provides
    @Singleton
    fun provideGetNewsByLanguageUseCase(topHeadlineRepository: TopHeadlineRepository): GetNewsByLanguageUseCase {
        return GetNewsByLanguageUseCase(topHeadlineRepository)
    }

    @Provides
    @Singleton
    fun provideNetworkTopHeadlineUseCases(
        getTopHeadlineUseCase: GetNetworkTopHeadlineUseCase,
        getNewsByLanguageUseCase: GetNewsByLanguageUseCase,
        getNewsBySourceUseCase: GetNewsBySourceUseCase
    ): NetworkTopHeadlineUseCases {
        return NetworkTopHeadlineUseCases(
            getTopHeadlineUseCase,
            getNewsByLanguageUseCase,
            getNewsBySourceUseCase
        )
    }

}