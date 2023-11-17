package com.sagrawal.newsapp.di.component

import com.sagrawal.newsapp.di.ActivityScope
import com.sagrawal.newsapp.di.module.ActivityModule
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}