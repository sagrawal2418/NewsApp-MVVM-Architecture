package com.sagrawal.newsapp.data.api


import com.sagrawal.newsapp.domain.model.NewsSourceResponse
import com.sagrawal.newsapp.domain.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(@Query("country") country: String): NewsSourceResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String?): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") language: String): TopHeadlinesResponse

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesResponse

}