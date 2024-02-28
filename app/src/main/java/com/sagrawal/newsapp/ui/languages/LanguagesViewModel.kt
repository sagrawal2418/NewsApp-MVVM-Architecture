package com.sagrawal.newsapp.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val languageRepository: LanguagesRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    fun fetchLanguages() {
        viewModelScope.launch(dispatcherProvider.main) {
            languageRepository.getLanguages()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}