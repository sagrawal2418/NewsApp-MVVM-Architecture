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
import com.sagrawal.newsapp.domain.usecase.topheadline.GetNewsByLanguageUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.GetNewsBySourceUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.GetTopHeadlineUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.TopHeadlineUseCases
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
    fun provideGetTopHeadlineUseCase(topHeadlineRepository: TopHeadlineRepository): GetTopHeadlineUseCase {
        return GetTopHeadlineUseCase(topHeadlineRepository)
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
    fun provideTopHeadlineUseCases(
        getTopHeadlineUseCase: GetTopHeadlineUseCase,
        getNewsByLanguageUseCase: GetNewsByLanguageUseCase,
        getNewsBySourceUseCase: GetNewsBySourceUseCase
    ): TopHeadlineUseCases {
        return TopHeadlineUseCases(
            getTopHeadlineUseCase,
            getNewsByLanguageUseCase,
            getNewsBySourceUseCase
        )
    }

}