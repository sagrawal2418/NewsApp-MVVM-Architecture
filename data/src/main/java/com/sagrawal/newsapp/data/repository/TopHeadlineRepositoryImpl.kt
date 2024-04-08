package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.local.DatabaseService
import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.toArticleEntity
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepositoryImpl @Inject constructor(
    private val databaseService: DatabaseService,
    private val networkService: NetworkService
) : TopHeadlineRepository {

    @OptIn(FlowPreview::class)
    override fun getTopHeadlines(country: String): Flow<List<Article>> {

        return flow { emit(networkService.getTopHeadlines(country)) }
            .map { response ->
                response.apiArticles.map { it.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll((articles))) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    override fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return databaseService.getArticles()
    }

    override fun getNewsBySources(query: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsBySources(query)) }
            .map { response ->
                response.apiArticles.map { it.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll((articles))) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    override fun getNewsByLanguage(query: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsByLanguage(query)) }
            .map { response ->
                response.apiArticles.map { it.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll((articles))) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

}