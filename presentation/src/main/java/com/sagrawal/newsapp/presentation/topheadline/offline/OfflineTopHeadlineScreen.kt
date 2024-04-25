package com.sagrawal.newsapp.presentation.topheadline.offline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.sagrawal.newsapp.domain.local.entity.Article
import com.sagrawal.newsapp.domain.local.entity.Source
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.BannerImage
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.DescriptionText
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.SourceText
import com.sagrawal.newsapp.presentation.base.TitleText
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.base.calculateBottomNavigationBarHeight

@Composable
fun OfflineTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: OfflineTopHeadlineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(R.string.offline_topheadlines)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OfflineTopHeadlineScreen(uiState, viewModel, onNewsClick)
        }
    }
}


@Composable
fun OfflineTopHeadlineScreen(
    uiState: UiState<List<Article>>,
    viewModel: OfflineTopHeadlineViewModel,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
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
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = calculateBottomNavigationBarHeight())
    ) {
        items(articles, key = { article -> article.url }) { article ->
            Article(article, onNewsClick)
        }
    }
}

@Composable
fun Article(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        BannerImage(article.imageUrl, article.title)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.source.name)
    }
}
