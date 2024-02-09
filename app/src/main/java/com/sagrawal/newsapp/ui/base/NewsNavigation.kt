package com.sagrawal.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagrawal.newsapp.ui.countries.CountriesRoute
import com.sagrawal.newsapp.ui.languages.LanguageRoute
import com.sagrawal.newsapp.ui.main.MainRoute
import com.sagrawal.newsapp.ui.newssources.NewsSourcesRoute
import com.sagrawal.newsapp.ui.search.SearchRoute
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineRoute

sealed class Route(val name: String) {
    object MainScreen : Route("main")
    object TopHeadlineScreen : Route("topheadline")
    object NewsSourceScreen : Route("newsSource")
    object LanguageScreen : Route("language")
    object CountryScreen : Route("country")
    object SearchScreen : Route("search")

}

@Composable
fun NewsNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.name
    ) {
        composable(route = Route.MainScreen.name) {
            MainRoute(navController)
        }
        composable(route = Route.TopHeadlineScreen.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.NewsSourceScreen.name) {
            NewsSourcesRoute(navController)
        }
        composable(route = Route.LanguageScreen.name) {
            LanguageRoute(navController)
        }
        composable(route = Route.CountryScreen.name) {
            CountriesRoute(navController)
        }
        composable(route = Route.SearchScreen.name) {
            SearchRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}



