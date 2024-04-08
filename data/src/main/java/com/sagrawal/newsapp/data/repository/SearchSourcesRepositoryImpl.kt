package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.local.AppDatabaseService
import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.toArticleEntity
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchSourcesRepositoryImpl @Inject constructor(

    private val databaseService: AppDatabaseService,

    private val networkService: NetworkService) :
    SearchSourcesRepository {

    override fun getNewsSourcesByQueries(queries: String): Flow<List<Article>> {

        return flow { emit(networkService.getNewsByQueries(queries)) }
            .map { response ->
                response.apiArticles.map { it.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll((articles))) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

}