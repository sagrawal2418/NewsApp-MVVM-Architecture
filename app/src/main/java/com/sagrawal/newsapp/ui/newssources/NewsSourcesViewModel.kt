package com.sagrawal.newsapp.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
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
class NewsSourcesViewModel @Inject constructor(
    private val newsSourcesRepository: NewsSourcesRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<NewsSource>>> = _uiState

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch(dispatcherProvider.main) {
            newsSourcesRepository.getNewsSources(COUNTRY)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}