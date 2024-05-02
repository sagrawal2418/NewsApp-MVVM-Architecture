package com.sagrawal.newsapp.data.local

import com.sagrawal.newsapp.domain.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(articles: List<Article>)

}