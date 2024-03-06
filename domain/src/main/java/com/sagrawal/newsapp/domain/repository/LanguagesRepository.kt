package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguagesRepository {

    fun getLanguages(): Flow<List<Language>>
}