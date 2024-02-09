package com.sagrawal.newsapp.ui.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.ui.base.Route
import com.sagrawal.newsapp.ui.base.ShowError
import com.sagrawal.newsapp.ui.base.ShowLoading
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesRoute(
    navHostController: NavHostController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Define the onCountryClick action
    val onCountryClick: (String) -> Unit = { id ->
        navHostController.navigate(Route.TopHeadlineScreen.name)
    }

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(uiState, onCountryClick)
        }
    })

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
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (country.name.isNotEmpty()) {
                onCountryClick(country.id)
            }
        }) {
        TitleText(country.name)
    }

}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}