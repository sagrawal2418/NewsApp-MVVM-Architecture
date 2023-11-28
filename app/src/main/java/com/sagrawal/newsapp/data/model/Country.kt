package com.sagrawal.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("country_name")
    val countryName: String = "",
    @SerializedName("country_code")
    val countryCode: String = ""
)