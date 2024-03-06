package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    NewsSourcesRepository {

    override fun getNewsSources(country: String): Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getNewsSources(country))
        }.map {
            it.sources
        }
    }

}