package com.sagrawal.newsapp.ui.search

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.repository.SearchSourcesRepository
import com.sagrawal.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchSourcesViewModel(private val searchSourcesRepository: SearchSourcesRepository) :
    ViewModel() {

    private var query: String? = null

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNewsByQueries(q: String?) {
        if (!TextUtils.isEmpty(q)) {
            this.query = q
        }
        viewModelScope.launch {
            query?.let {
                searchSourcesRepository.getNewsSourcesByQueries(it)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }
                    .collect { result ->
                        _uiState.value = UiState.Success(result)
                    }
            }
        }
    }
}