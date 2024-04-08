package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.model.ApiArticle
import kotlinx.coroutines.flow.Flow

interface TopHeadlineRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>

    fun getArticlesDirectlyFromDB(): Flow<List<Article>>

    fun getNewsBySources(query: String): Flow<List<Article>>

    fun getNewsByLanguage(query: String): Flow<List<Article>>
}