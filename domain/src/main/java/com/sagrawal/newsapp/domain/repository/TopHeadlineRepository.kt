package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface TopHeadlineRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>

    fun getNewsBySources(query: String): Flow<List<Article>>

    fun getNewsByLanguage(query: String): Flow<List<Article>>
}