package com.sagrawal.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class NewsSourceResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<NewsSource> = ArrayList(),
)