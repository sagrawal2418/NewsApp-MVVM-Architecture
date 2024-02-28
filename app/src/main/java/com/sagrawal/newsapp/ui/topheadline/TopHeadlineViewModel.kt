package com.sagrawal.newsapp.ui.topheadline

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import com.sagrawal.newsapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val dispatcherProvider: DispatcherProvider,
    savedStateHandle: SavedStateHandle // For receiving navigation arguments
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val newsId: String? = savedStateHandle["newsId"]

    private val country: String? = savedStateHandle["country"]

    private val language: String? = savedStateHandle["language"]


    init {
        loadTopHeadlines()
    }

    // Method to load headlines, optionally filtered by ID
    fun loadTopHeadlines() {
        viewModelScope.launch(dispatcherProvider.main) {
            if (newsId?.isNotEmpty() == true) {
                topHeadlineRepository.getNewsBySources(newsId)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            } else if (language?.isNotEmpty() == true) {
                topHeadlineRepository.getNewsByLanguage(language)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            } else {
                topHeadlineRepository.getTopHeadlines(country ?: COUNTRY)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

}