package com.sagrawal.newsapp.domain.usecase

import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsSourcesUseCase @Inject constructor(private val newsSourcesRepository: NewsSourcesRepository) {

    operator fun invoke(country: String): Flow<List<NewsSource>> {
        return newsSourcesRepository.getNewsSources(country)
    }

}