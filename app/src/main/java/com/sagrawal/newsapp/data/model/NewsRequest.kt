package com.sagrawal.newsapp.data.model

data class NewsRequest(
    val news: String? = null,
    val language: String? = null,
    val country: String? = null
)