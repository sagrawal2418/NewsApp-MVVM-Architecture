package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchSourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSourcesByQueries(queries: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByQueries(queries))
        }.map {
            it.articles
        }
    }

}