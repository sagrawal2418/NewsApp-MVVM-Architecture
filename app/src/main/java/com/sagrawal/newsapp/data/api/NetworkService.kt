package com.sagrawal.newsapp.data.api

import com.sagrawal.newsapp.data.model.NewsSourceResponse
import com.sagrawal.newsapp.data.model.TopHeadlinesResponse
import com.sagrawal.newsapp.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesBySources(@Query("sources") sources: String?): TopHeadlinesResponse


    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(@Query("country") country: String): NewsSourceResponse
}