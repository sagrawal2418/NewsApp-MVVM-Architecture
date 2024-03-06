package com.sagrawal.newsapp.domain.usecase.topheadline

import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsByLanguageUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(query: String): Flow<List<Article>> {
        return topHeadlineRepository.getNewsByLanguage(query)
    }

}