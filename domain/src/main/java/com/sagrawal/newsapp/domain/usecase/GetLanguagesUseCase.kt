package com.sagrawal.newsapp.domain.usecase

import com.sagrawal.newsapp.domain.model.Language
import com.sagrawal.newsapp.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguagesUseCase @Inject constructor(private val languagesRepository: LanguagesRepository) {

    operator fun invoke(): Flow<List<Language>> {
        return languagesRepository.getLanguages()
    }
}