package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagesRepository @Inject constructor() {

    fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(AppConstant.LANGUAGES)
        }
    }
}