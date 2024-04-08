package com.sagrawal.newsapp.domain.local.entity

import androidx.room.ColumnInfo
import com.sagrawal.newsapp.domain.model.ApiSource

data class Source(
    @ColumnInfo(name = "sourceId")
    val id: String?,
    @ColumnInfo(name = "sourceName")
    val name: String = ""
)