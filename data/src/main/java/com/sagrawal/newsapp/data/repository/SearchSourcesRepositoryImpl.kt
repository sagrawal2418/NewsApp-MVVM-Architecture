package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchSourcesRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) :
    SearchSourcesRepository {

    override fun getNewsSourcesByQueries(queries: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByQueries(queries))
        }.map {
            it.apiArticles
        }
    }

}