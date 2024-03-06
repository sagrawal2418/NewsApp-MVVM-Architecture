package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.util.ListUtil
import com.sagrawal.newsapp.domain.model.Language
import com.sagrawal.newsapp.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LanguagesRepositoryImpl @Inject constructor() : LanguagesRepository {

    override fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(ListUtil.LANGUAGES)
        }
    }
}