package com.sagrawal.newsapp.presentation.topheadline.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.usecase.topheadline.GetPagingTopHeadlineUseCase
import com.sagrawal.newsapp.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingTopHeadlineViewModel @Inject constructor(
    private val useCase: GetPagingTopHeadlineUseCase,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<ApiArticle>> = _uiState

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch(dispatcherProvider.main) {
            useCase.invoke()
                .flowOn(dispatcherProvider.io).cachedIn(viewModelScope)
                .collect {
                    _uiState.value = it
                }
        }
    }

}