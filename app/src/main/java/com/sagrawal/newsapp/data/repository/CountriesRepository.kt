package com.sagrawal.newsapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getCountries(fileName: String): List<Country> {
        return parseJsonToCountryList(context.assets.open(fileName).bufferedReader().use {
            it.readText()
        })
    }

    private fun parseJsonToCountryList(jsonString: String): List<Country> {
        return Gson().fromJson(jsonString, object : TypeToken<List<Country>>() {}.type)
    }
}