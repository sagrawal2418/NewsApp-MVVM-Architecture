package com.sagrawal.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val logger: Logger
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private var newsSourceId: String? = null
    private var languageSourceId: String? = null
    private var countrySourceId: String? = null

    private fun fetchTopHeadlines(country: String? = AppConstant.COUNTRY) {
        viewModelScope.launch {
            if (country != null) {
                topHeadlineRepository.getTopHeadlines(country)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d("TopHeadlineViewModel", "fetching top headlines failed")
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

    private fun fetchNewsBySources(sources: String?) {
        viewModelScope.launch {
            if (sources != null) {
                topHeadlineRepository.getNewsBySources(sources)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

    private fun fetchNewsByLanguage(languageSource: String?) {
        viewModelScope.launch {
            if (languageSource != null) {
                topHeadlineRepository.getNewsByLanguage(languageSource)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

    fun init(newsSourceId: String?, languageSourceId: String?, countrySourceId: String?) {
        this.newsSourceId = newsSourceId
        this.languageSourceId = languageSourceId
        this.countrySourceId = countrySourceId
    }

    fun makeServiceCall() {
        when {
            newsSourceId != null -> fetchNewsBySources(newsSourceId)
            languageSourceId != null -> fetchNewsByLanguage(languageSourceId)
            countrySourceId != null -> fetchTopHeadlines(countrySourceId)
            else -> fetchTopHeadlines()
        }
    }
}