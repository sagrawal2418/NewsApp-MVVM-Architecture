package com.sagrawal.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.sagrawal.newsapp.data.model.Source

data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val source: Source
)