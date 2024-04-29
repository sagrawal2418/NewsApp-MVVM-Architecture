package com.sagrawal.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesByQueriesUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.sagrawal.newsapp.utils.AppConstant.MIN_SEARCH_CHAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetNewsSourcesByQueriesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    val query = MutableStateFlow("")

    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun createNewsFlow() {
        viewModelScope.launch(dispatcherProvider.main) {
            query.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if (it.isNotEmpty() && it.length >= MIN_SEARCH_CHAR) {
                        return@filter true
                    } else {
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest useCase.invoke(it)
                        .catch { e ->
                            // handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(dispatcherProvider.io)
                .collect {
                    // handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}