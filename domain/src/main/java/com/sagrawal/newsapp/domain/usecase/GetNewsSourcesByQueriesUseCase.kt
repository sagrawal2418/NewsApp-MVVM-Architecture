package com.sagrawal.newsapp.domain.usecase

import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsSourcesByQueriesUseCase @Inject constructor(private val searchSourcesRepository: SearchSourcesRepository) {

    operator fun invoke(query: String): Flow<List<Article>> {
        return searchSourcesRepository.getNewsSourcesByQueries(query)
    }

}