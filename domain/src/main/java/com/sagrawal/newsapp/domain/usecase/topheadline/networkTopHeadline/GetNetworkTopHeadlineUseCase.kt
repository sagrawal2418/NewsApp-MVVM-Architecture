package com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline

import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNetworkTopHeadlineUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(country: String): Flow<List<ApiArticle>> {
        return topHeadlineRepository.getNetworkTopHeadlines(country)
    }

}