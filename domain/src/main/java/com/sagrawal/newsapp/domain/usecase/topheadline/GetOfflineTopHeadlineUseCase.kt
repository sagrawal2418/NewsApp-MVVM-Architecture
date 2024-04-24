package com.sagrawal.newsapp.domain.usecase.topheadline

import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetOfflineTopHeadlineUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(country: String): Flow<List<Article>> {
        return topHeadlineRepository.getTopHeadlines(country)
    }

    operator fun invoke(): Flow<List<Article>> {
        return topHeadlineRepository.getArticlesDirectlyFromDB()
    }

}