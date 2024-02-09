package com.sagrawal.newsapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.R
import com.sagrawal.newsapp.ui.base.Route
import com.sagrawal.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRoute(navController: NavHostController) {

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MainScreen(navController)
        }
    })

}

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(all = 16.dp)
    ) {
        Button(
            onClick = { navController.navigate(Route.TopHeadlineScreen.name) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.top_headlines_button))
        }

        Button(
            onClick = { navController.navigate(Route.NewsSourceScreen.name) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        ) {
            Text(text = stringResource(id = R.string.news_sources_button))
        }

        Button(
            onClick = { navController.navigate(Route.CountryScreen.name) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        ) {
            Text(text = stringResource(id = R.string.countries_button))
        }

        Button(
            onClick = { navController.navigate(Route.LanguageScreen.name) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        ) {
            Text(text = stringResource(id = R.string.languages_button))
        }

        Button(
            onClick = { navController.navigate(Route.SearchScreen.name) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        ) {
            Text(text = stringResource(id = R.string.search_button))
        }
    }
}
