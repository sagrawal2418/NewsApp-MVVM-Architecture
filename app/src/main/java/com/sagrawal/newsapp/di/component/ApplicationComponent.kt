package com.sagrawal.newsapp.di.component

import android.content.Context
import com.sagrawal.newsapp.NewsApplication
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
import com.sagrawal.newsapp.data.repository.SearchSourcesRepository
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.di.ApplicationContext
import com.sagrawal.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourcesRepository(): NewsSourcesRepository

    fun getCountriesRepository(): CountriesRepository

    fun getLanguagesRepository(): LanguagesRepository

    fun getSearchSourcesRepository(): SearchSourcesRepository



}