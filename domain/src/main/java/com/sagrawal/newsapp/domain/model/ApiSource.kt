package com.sagrawal.newsapp.domain.model

import com.google.gson.annotations.SerializedName
import com.sagrawal.newsapp.domain.local.entity.Source

data class ApiSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    return Source(id, name)
}