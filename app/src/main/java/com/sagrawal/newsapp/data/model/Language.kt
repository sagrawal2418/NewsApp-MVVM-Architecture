package com.sagrawal.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("code")
    val code: String = ""

)