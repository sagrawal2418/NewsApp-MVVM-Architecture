package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.model.ApiArticle
import kotlinx.coroutines.flow.Flow

interface SearchSourcesRepository {

    fun getNewsSourcesByQueries(queries: String): Flow<List<ApiArticle>>

}