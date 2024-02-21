package com.sagrawal.newsapp.ui.countries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.ui.base.Route
import com.sagrawal.newsapp.ui.base.ShowCards
import com.sagrawal.newsapp.ui.base.ShowError
import com.sagrawal.newsapp.ui.base.ShowLoading
import com.sagrawal.newsapp.ui.base.UiState

@Composable
fun CountriesRoute(
    navHostController: NavHostController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Define the onNewsClick action
    val onNewsClick: (String) -> Unit = { country ->
        val route = Route.topHeadlineScreenWithCountry(country)
        navHostController.navigate(route)
    }

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(uiState, onNewsClick)
        }
    }
}


@Composable
fun CountriesScreen(uiState: UiState<List<Country>>, onCountryClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            LanguagesList(uiState.data, onCountryClick)
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
fun LanguagesList(countries: List<Country>, onCountryClick: (url: String) -> Unit) {
    LazyColumn {
        items(countries, key = { country -> country.id }) { country ->
            Country(country, onCountryClick)
        }
    }
}

@Composable
fun Country(country: Country, onCountryClick: (url: String) -> Unit) {
    ShowCards(
        title = country.name,
        id = country.id,
        onClick = onCountryClick
    )

}