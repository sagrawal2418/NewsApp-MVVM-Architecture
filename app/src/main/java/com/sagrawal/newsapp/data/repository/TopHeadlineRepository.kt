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

    fun getTopHeadlines(query: String, byCountry: Boolean = true): Flow<List<Article>> {
        return flow {
            if (byCountry) {
                emit(networkService.getTopHeadlinesByCountry(query))
            } else {
                emit(networkService.getTopHeadlinesBySources(query))
            }
        }.map {
            it.articles
        }
    }

}