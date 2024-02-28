package com.sagrawal.newsapp.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.R
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.ui.base.BannerImage
import com.sagrawal.newsapp.ui.base.ShowError
import com.sagrawal.newsapp.ui.base.ShowLoading
import com.sagrawal.newsapp.ui.base.TitleText
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.topheadline.DescriptionText
import com.sagrawal.newsapp.ui.topheadline.SourceText

@Composable
fun SearchRoute(
    navHostController: NavHostController,
    onNewsClick: (url: String) -> Unit, viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SearchBar(onQuerySubmitted = viewModel::searchNews)
            SearchContent(uiState, viewModel, onNewsClick)
        }
    }
}

@Composable
fun SearchBar(onQuerySubmitted: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = { newValue -> query = newValue },
        label = { Text("Search News") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onQuerySubmitted(query)
        })
    )
}

@Composable
fun SearchContent(
    uiState: UiState<List<Article>>,
    viewModel: SearchViewModel?,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Success -> {
            SearchResultList(searchResults = uiState.data, onNewsClick)
        }

        is UiState.Error -> {
            ShowError(
                text = stringResource(id = R.string.something_went_wrong),
                retryEnabled = true
            ) {
                viewModel?.createNewsFlow()
            }
        }
    }
}

@Composable
fun SearchResultList(searchResults: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(searchResults) { article ->
            SearchResultItem(article = article, onNewsClick)
        }
    }
}

@Composable
fun SearchResultItem(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        BannerImage(article)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.source)
    }
}



