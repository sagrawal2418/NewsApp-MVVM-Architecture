package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    TopHeadlineRepository {

    override fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    override fun getNewsBySources(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySources(query))
        }.map {
            it.articles
        }
    }

    override fun getNewsByLanguage(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByLanguage(query))
        }.map {
            it.articles
        }
    }

}