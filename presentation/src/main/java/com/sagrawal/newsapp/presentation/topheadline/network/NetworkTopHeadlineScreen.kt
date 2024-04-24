package com.sagrawal.newsapp.presentation.topheadline.network

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.Article
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.TitleText
import com.sagrawal.newsapp.presentation.base.UiState

@Composable
fun NetworkTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NetworkTopHeadlineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(R.string.network_topheadlines)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NetworkTopHeadlineScreen(uiState, viewModel, onNewsClick)
        }
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
    LazyColumn {
        items(articles, key = { article -> article.url }) { article ->
            Article(article, onNewsClick)
        }
    }
}
