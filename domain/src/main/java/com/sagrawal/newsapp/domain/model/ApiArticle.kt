package com.sagrawal.newsapp.domain.model

import com.google.gson.annotations.SerializedName
import com.sagrawal.newsapp.domain.local.entity.Article
import java.util.UUID

data class ApiArticle(
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
    val apiSource: ApiSource
)

fun ApiArticle.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = apiSource.toSourceEntity()
    )
}