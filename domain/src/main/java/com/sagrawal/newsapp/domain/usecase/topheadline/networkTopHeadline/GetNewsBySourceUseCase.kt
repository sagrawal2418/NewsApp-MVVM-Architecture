package com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline

import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsBySourceUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(query: String): Flow<List<ApiArticle>> {
        return topHeadlineRepository.getNewsBySources(query)
    }

}