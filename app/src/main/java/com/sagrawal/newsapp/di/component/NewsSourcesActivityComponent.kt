package com.sagrawal.newsapp.di.component

import com.sagrawal.newsapp.di.NewsSourcesActivityScope
import com.sagrawal.newsapp.di.module.NewsSourcesActivityModule
import com.sagrawal.newsapp.ui.newssources.NewsSourcesActivity
import dagger.Component

@NewsSourcesActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [NewsSourcesActivityModule::class])
interface NewsSourcesActivityComponent {

    fun inject(activity: NewsSourcesActivity)

}