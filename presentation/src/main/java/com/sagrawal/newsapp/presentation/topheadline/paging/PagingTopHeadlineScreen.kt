package com.sagrawal.newsapp.presentation.topheadline.paging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.Article
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.calculateBottomNavigationBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagingTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: PagingTopHeadlineViewModel = hiltViewModel()
) {
    val articles = viewModel.uiState.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(R.string.paging_topheadlines)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            PagingTopHeadlineScreen(viewModel, articles, onNewsClick)
        }
    }
}

@Composable
fun PagingTopHeadlineScreen(
    viewModel: PagingTopHeadlineViewModel,
    articles: LazyPagingItems<ApiArticle>,
    onNewsClick: (url: String) -> Unit
) {

    ArticleList(articles, onNewsClick)

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.refresh is LoadState.Error -> {
                ShowError(
                    text = stringResource(id = R.string.something_went_wrong),
                    retryEnabled = true
                ) {
                    viewModel.getNews()
                }
            }

            loadState.append is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.append is LoadState.Error -> {
                ShowError(
                    text = stringResource(id = R.string.something_went_wrong),
                    retryEnabled = true
                ) {
                    viewModel.getNews()
                }
            }
        }
    }
}

@Composable
fun ArticleList(articles: LazyPagingItems<ApiArticle>, onNewsClick: (url: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = calculateBottomNavigationBarHeight())
    ) {
        items(articles.itemCount, key = { index ->
            articles[index]?.let { "${it.url}_$index" }!!  // Safely generate a unique key, appending index
        }) { index ->
            articles[index]?.let { article ->
                Article(article, onNewsClick)
            }
        }
    }
}
