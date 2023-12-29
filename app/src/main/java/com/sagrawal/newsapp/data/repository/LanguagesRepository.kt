package com.sagrawal.newsapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.di.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getLanguages(fileName: String): Flow<List<Language>> {
        return flow {
            emit(parseJsonToLanguageList(context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }))
        }
    }

    private fun parseJsonToLanguageList(jsonString: String): List<Language> {
        return Gson().fromJson(jsonString, object : TypeToken<List<Language>>() {}.type)
    }
}