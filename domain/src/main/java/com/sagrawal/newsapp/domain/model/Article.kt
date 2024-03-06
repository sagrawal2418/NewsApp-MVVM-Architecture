package com.sagrawal.newsapp.domain.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Article(
    val uniqueId: String = UUID.randomUUID().toString(), // Auto-generate unique ID

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