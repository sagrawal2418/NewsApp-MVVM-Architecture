package com.sagrawal.newsapp.ui.newssources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.ui.base.Route
import com.sagrawal.newsapp.ui.base.ShowCards
import com.sagrawal.newsapp.ui.base.ShowError
import com.sagrawal.newsapp.ui.base.ShowLoading
import com.sagrawal.newsapp.ui.base.UiState

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

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSourcesScreen(uiState, onNewsClick)
        }
    }

}


@Composable
fun NewsSourcesScreen(uiState: UiState<List<NewsSource>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            NewsSourceList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
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