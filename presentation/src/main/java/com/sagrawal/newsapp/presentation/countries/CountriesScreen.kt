package com.sagrawal.newsapp.presentation.countries

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
import com.sagrawal.newsapp.domain.model.Country
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.ShowCards
import com.sagrawal.newsapp.presentation.base.ShowError
import com.sagrawal.newsapp.presentation.base.ShowLoading
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.Route
import com.sagrawal.newsapp.presentation.base.UiState

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(topBar = {
        CustomTopAppBar(
            title = stringResource(R.string.countries_list)
        )
    }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(uiState, viewModel, onNewsClick)
        }
    }
}


@Composable
fun CountriesScreen(
    uiState: UiState<List<Country>>,
    viewModel: CountriesViewModel?,
    onCountryClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            CountriesList(uiState.data, onCountryClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(
                text = stringResource(id = R.string.something_went_wrong),
                retryEnabled = true
            ) {
                viewModel?.fetchCountries()
            }
        }
    }
}

@Composable
fun CountriesList(countries: List<Country>, onCountryClick: (url: String) -> Unit) {
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