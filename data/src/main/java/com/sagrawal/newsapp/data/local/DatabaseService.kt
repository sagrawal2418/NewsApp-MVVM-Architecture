package com.sagrawal.newsapp.data.local

import kotlinx.coroutines.flow.Flow
import com.sagrawal.newsapp.domain.local.entity.Article

interface DatabaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(articles: List<Article>)

}