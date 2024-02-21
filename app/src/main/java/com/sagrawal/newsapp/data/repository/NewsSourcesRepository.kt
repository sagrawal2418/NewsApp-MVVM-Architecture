package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.data.network.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(country: String): Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getNewsSources(country))
        }.map {
            it.sources
        }
    }

}