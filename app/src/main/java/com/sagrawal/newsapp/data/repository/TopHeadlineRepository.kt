package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    fun getNewsBySources(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySources(query))
        }.map {
            it.articles
        }
    }

    fun getNewsByLanguage(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByLanguage(query))
        }.map {
            it.articles
        }
    }

}