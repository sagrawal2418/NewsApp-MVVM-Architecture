package com.sagrawal.newsapp.data.network

import com.sagrawal.newsapp.data.model.NewsSourceResponse
import com.sagrawal.newsapp.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(@Query("country") country: String): NewsSourceResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String?): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") language: String): TopHeadlinesResponse

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesResponse
}