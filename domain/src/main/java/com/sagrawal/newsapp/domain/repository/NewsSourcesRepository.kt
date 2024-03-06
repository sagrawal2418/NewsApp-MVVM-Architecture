package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsSourcesRepository {

    fun getNewsSources(country: String): Flow<List<NewsSource>>

}