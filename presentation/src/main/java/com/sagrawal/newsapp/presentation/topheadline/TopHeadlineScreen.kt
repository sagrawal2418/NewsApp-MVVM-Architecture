package com.sagrawal.newsapp.presentation.topheadline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.Route
import com.sagrawal.newsapp.presentation.base.TitleTextLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineRoute(navHostController: NavHostController) {
    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(R.string.headlines)) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(navHostController)
        }
    }
}

@Composable
fun TopHeadlineScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonWithText(stringResource(R.string.network_topheadlines)) {
            val route = Route.networkTopHeadlineScreen()
            navHostController.navigate(route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithText(stringResource(R.string.offline_topheadlines)) {
            val route = Route.offlineTopHeadlineScreen()
            navHostController.navigate(route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithText(stringResource(R.string.paging_topheadlines)) {
            val route = Route.pagingTopHeadlineScreen()
            navHostController.navigate(route)
        }
    }
}

@Composable
fun ButtonWithText(text: String, onNewsClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onNewsClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            TitleTextLarge(text)
        }
    }
}
