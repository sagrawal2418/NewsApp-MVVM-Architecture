package com.sagrawal.newsapp.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguagesViewModel @Inject constructor(
    private val languageRepository: LanguagesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            try {
                val languages = languageRepository.getLanguages("languages.json")
                _uiState.value = UiState.Success(languages)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch languages")
            }
        }
    }

}