package com.sagrawal.newsapp.domain.usecase

import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsSourcesByQueriesUseCase @Inject constructor(private val searchSourcesRepository: SearchSourcesRepository) {

    operator fun invoke(query: String): Flow<List<ApiArticle>> {
        return searchSourcesRepository.getNewsSourcesByQueries(query)
    }

}