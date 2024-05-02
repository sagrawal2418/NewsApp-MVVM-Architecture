package com.sagrawal.newsapp.presentation.topheadline.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.usecase.topheadline.GetOfflineTopHeadlineUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.util.NetworkHelper
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineTopHeadlineViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val useCase: GetOfflineTopHeadlineUseCase,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines() {
        viewModelScope.launch(dispatcherProvider.io) {
            if (networkHelper.isNetworkConnected()) {
                useCase.invoke(COUNTRY)
            } else {
                useCase.invoke()
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