package com.sagrawal.newsapp.presentation.newssources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.Route
import com.sagrawal.newsapp.presentation.base.ShowCards
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourcesRoute(
    navHostController: NavHostController,
    viewModel: NewsSourcesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Define the onNewsClick action
    val onNewsClick: (String) -> Unit = { newsId ->
        val route = Route.topHeadlineScreenWithId(newsId)
        navHostController.navigate(route)
    }

    Scaffold(topBar = { CustomTopAppBar(title = stringResource(R.string.news_sources)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSourcesScreen(uiState, viewModel, onNewsClick)
        }
    }

}


@Composable
fun NewsSourcesScreen(
    uiState: UiState<List<NewsSource>>,
    viewModel: NewsSourcesViewModel?,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            NewsSourceList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(
                text = stringResource(id = R.string.something_went_wrong),
                retryEnabled = true
            ) {
                viewModel?.fetchNews()
            }
        }
    }
}

@Composable
fun NewsSourceList(newsSources: List<NewsSource>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(newsSources, key = { newsSource -> newsSource.url }) { newsSource ->
            NewsSource(newsSource, onNewsClick)
        }
    }
}

@Composable
fun NewsSource(newsSource: NewsSource, onNewsClick: (url: String) -> Unit) {
    newsSource.id?.let { ShowCards(title = newsSource.name, id = it, onClick = onNewsClick) }
}