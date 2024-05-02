package com.sagrawal.newsapp.presentation.topheadline.network

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.Article
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.base.calculateBottomNavigationBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NetworkTopHeadlineViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        NetworkTopHeadlineScreen(uiState, viewModel, onNewsClick)
    }
}


@Composable
fun NetworkTopHeadlineScreen(
    uiState: UiState<List<ApiArticle>>,
    viewModel: NetworkTopHeadlineViewModel,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ApiArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(
                text = stringResource(id = R.string.something_went_wrong),
                retryEnabled = true
            ) {
                viewModel.loadTopHeadlines()
            }
        }
    }

}

@Composable
fun ApiArticleList(articles: List<ApiArticle>, onNewsClick: (url: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = calculateBottomNavigationBarHeight()
        )
    ) {
        itemsIndexed(articles) { index, article ->
            key(article.url + index) {  // Create a unique key by appending the index
                Article(article, onNewsClick)
            }
        }
    }
}
