package com.sagrawal.newsapp.presentation.languages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.sagrawal.newsapp.domain.model.Language
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.Route
import com.sagrawal.newsapp.presentation.base.ShowCards
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.base.calculateBottomNavigationBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageRoute(
    navHostController: NavHostController,
    viewModel: LanguagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Define the onNewsClick action
    val onNewsClick: (String) -> Unit = { language ->
        val route = Route.topHeadlineScreenWithLanguage(language)
        navHostController.navigate(route)
    }

    Scaffold(topBar = { CustomTopAppBar(title = stringResource(R.string.language_list)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LanguagesScreen(uiState, viewModel, onNewsClick)
        }
    }

}


@Composable
fun LanguagesScreen(
    uiState: UiState<List<Language>>,
    viewModel: LanguagesViewModel?,
    onLanguageClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            LanguagesList(uiState.data, onLanguageClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(
                text = stringResource(id = R.string.something_went_wrong),
                retryEnabled = true
            ) {
                viewModel?.fetchLanguages()
            }
        }
    }
}

@Composable
fun LanguagesList(languages: List<Language>, onLanguageClick: (url: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = calculateBottomNavigationBarHeight())
    ) {
        items(languages, key = { language -> language.id }) { language ->
            Language(language, onLanguageClick)
        }
    }
}

@Composable
fun Language(language: Language, onLanguageClick: (url: String) -> Unit) {
    ShowCards(
        title = language.name,
        id = language.id,
        onClick = onLanguageClick
    )

}