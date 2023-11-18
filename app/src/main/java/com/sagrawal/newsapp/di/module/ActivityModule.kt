package com.sagrawal.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
import com.sagrawal.newsapp.di.NewsSourcesActivityContext
import com.sagrawal.newsapp.ui.base.ViewModelProviderFactory
import com.sagrawal.newsapp.ui.newssources.NewsSourcesAdapter
import com.sagrawal.newsapp.ui.newssources.NewsSourcesViewModel
import dagger.Module
import dagger.Provides

@Module
class NewsSourcesActivityModule(private val activity: AppCompatActivity) {

    @NewsSourcesActivityContext
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
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(activity, ArrayList())

}