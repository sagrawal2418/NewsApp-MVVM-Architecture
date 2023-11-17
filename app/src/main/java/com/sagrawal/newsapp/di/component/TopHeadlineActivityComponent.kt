package com.sagrawal.newsapp.di.component

import com.sagrawal.newsapp.di.TopHeadlineActivityScope
import com.sagrawal.newsapp.di.module.TopHeadlineActivityModule
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@TopHeadlineActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [TopHeadlineActivityModule::class])
interface TopHeadlineActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}