package com.sagrawal.newsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsRequest(
    val news: String? = null,
    val language: String? = null,
    val country: String? = null
) : Parcelable