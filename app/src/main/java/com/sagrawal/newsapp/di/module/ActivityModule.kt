package com.sagrawal.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
import com.sagrawal.newsapp.data.repository.SearchSourcesRepository
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.di.ActivityContext
import com.sagrawal.newsapp.ui.base.ViewModelProviderFactory
import com.sagrawal.newsapp.ui.countries.CountriesAdapter
import com.sagrawal.newsapp.ui.countries.CountriesViewModel
import com.sagrawal.newsapp.ui.languages.LanguagesAdapter
import com.sagrawal.newsapp.ui.languages.LanguagesViewModel
import com.sagrawal.newsapp.ui.newssources.NewsSourcesAdapter
import com.sagrawal.newsapp.ui.newssources.NewsSourcesViewModel
import com.sagrawal.newsapp.ui.search.SearchViewModel
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineAdapter
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourcesRepository): NewsSourcesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourcesViewModel::class) {
                NewsSourcesViewModel(newsSourceRepository)
            })[NewsSourcesViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideCountriesViewModel(countriesRepository: CountriesRepository): CountriesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountriesViewModel::class) {
                CountriesViewModel(countriesRepository)
            })[CountriesViewModel::class.java]
    }

    @Provides
    fun provideLanguagesViewModel(languagesRepository: LanguagesRepository): LanguagesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(LanguagesViewModel::class) {
                LanguagesViewModel(languagesRepository)
            })[LanguagesViewModel::class.java]
    }

    @Provides
    fun provideSearchSourcesViewModel(searchSourcesRepository: SearchSourcesRepository): SearchViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(searchSourcesRepository)
            })[SearchViewModel::class.java]
    }


    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter()

    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())

    @Provides
    fun provideLanguagesAdapter() = LanguagesAdapter(ArrayList())

}