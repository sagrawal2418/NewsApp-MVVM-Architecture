package com.sagrawal.newsapp.di.module

import com.sagrawal.newsapp.ui.countries.CountriesAdapter
import com.sagrawal.newsapp.ui.languages.LanguagesAdapter
import com.sagrawal.newsapp.ui.newssources.NewsSourcesAdapter
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityScoped
    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter()

    @ActivityScoped
    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguagesAdapter() = LanguagesAdapter(ArrayList())

}