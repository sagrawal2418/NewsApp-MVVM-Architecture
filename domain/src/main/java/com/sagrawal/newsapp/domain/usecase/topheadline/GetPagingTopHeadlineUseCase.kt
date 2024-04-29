package com.sagrawal.newsapp.domain.usecase.topheadline

import androidx.paging.PagingData
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPagingTopHeadlineUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(): Flow<PagingData<ApiArticle>> {
        return topHeadlineRepository.getPagingTopHeadlines()
    }
}