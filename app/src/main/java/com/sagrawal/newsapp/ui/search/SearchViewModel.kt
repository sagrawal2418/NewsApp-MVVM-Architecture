package com.sagrawal.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.repository.SearchSourcesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.sagrawal.newsapp.utils.AppConstant.MIN_SEARCH_CHAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class SearchViewModel @Inject constructor(private val searchSourcesRepository: SearchSourcesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val query = MutableStateFlow("")

    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    private fun createNewsFlow() {
        viewModelScope.launch {
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
                    return@flatMapLatest searchSourcesRepository.getNewsSourcesByQueries(it)
                        .catch { e ->
                            // handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    // handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}