package com.sagrawal.newsapp.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(private val newsSourcesRepository: NewsSourcesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<NewsSource>>> = _uiState

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsSourcesRepository.getNewsSources(COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


}