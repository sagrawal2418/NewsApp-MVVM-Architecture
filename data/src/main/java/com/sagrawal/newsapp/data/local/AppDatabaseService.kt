package com.sagrawal.newsapp.data.local

import kotlinx.coroutines.flow.Flow
import com.sagrawal.newsapp.domain.local.entity.Article
import javax.inject.Inject

class AppDatabaseService @Inject constructor(private val appDatabase: AppDatabase) : DatabaseService {

    override fun getArticles(): Flow<List<Article>> {
        return appDatabase.articleDao().getAll()
    }

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        appDatabase.articleDao().deleteAllAndInsertAll(articles)
    }

}