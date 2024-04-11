package com.sagrawal.newsapp.presentation.topheadline

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.usecase.topheadline.TopHeadlineUseCases
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.util.NetworkHelper
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val useCases: TopHeadlineUseCases,
    private val dispatcherProvider: DispatcherProvider,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val newsId: String? = savedStateHandle[AppConstant.NAV_ARG_NEWS_ID]

    private val country: String? = savedStateHandle[AppConstant.NAV_ARG_COUNTRY]

    private val language: String? = savedStateHandle[AppConstant.NAV_ARG_LANGUAGE]


    init {
        loadTopHeadlines()
    }

    // Method to load headlines, optionally filtered by ID
    fun loadTopHeadlines() {
        viewModelScope.launch(dispatcherProvider.main) {
            if (newsId?.isNotEmpty() == true)
                if (networkHelper.isNetworkConnected()) {
                    useCases.getNewsBySourceUseCase.invoke(newsId)
                } else {
                    useCases.getTopHeadlineUseCase.invoke()
                }
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    } else if (language?.isNotEmpty() == true)
                if (networkHelper.isNetworkConnected()) {
                    useCases.getNewsByLanguageUseCase.invoke(language)
                } else {
                    useCases.getTopHeadlineUseCase.invoke()
                }
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    } else {
                if (networkHelper.isNetworkConnected()) {
                    useCases.getTopHeadlineUseCase.invoke(country ?: COUNTRY)
                } else {
                    useCases.getTopHeadlineUseCase.invoke()
                }
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