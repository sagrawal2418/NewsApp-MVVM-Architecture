package com.sagrawal.newsapp.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagrawal.newsapp.presentation.countries.CountriesRoute
import com.sagrawal.newsapp.presentation.languages.LanguageRoute
import com.sagrawal.newsapp.presentation.main.TabBarItem
import com.sagrawal.newsapp.presentation.main.TabView
import com.sagrawal.newsapp.presentation.newssources.NewsSourcesRoute
import com.sagrawal.newsapp.presentation.search.SearchRoute
import com.sagrawal.newsapp.presentation.topheadline.TopHeadlineRoute

object Route {

    const val TopHeadlineScreenNewsSources = "top-headline/newsId/{newsId}"

    const val TopHeadlineScreenNewsByCountry = "top-headline/country/{country}"

    const val TopHeadlineScreenNewsByLanguage = "top-headline/language/{language}"

    fun topHeadlineScreenWithCountry(country: String): String {
        return "top-headline/country/$country"
    }

    fun topHeadlineScreenWithId(newsId: String): String {
        return "top-headline/newsId/$newsId"
    }

    fun topHeadlineScreenWithLanguage(language: String): String {
        return "top-headline/language/$language"
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavHost(tabBarItems: List<TabBarItem>) {

    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
        NavHost(navController = navController, startDestination = "Headlines") {
            composable("Headlines") {
                TopHeadlineRoute(navController, onNewsClick = {
                    openCustomChromeTab(context, it)
                })
            }

            composable("Sources") {
                NewsSourcesRoute(navController)
            }
            composable("Countries") {
                CountriesRoute(navController)
            }
            composable("Languages") {
                LanguageRoute(navController)
            }
            composable("Search") {
                SearchRoute(navController, onNewsClick = {
                    openCustomChromeTab(context, it)
                })
            }

            composable(
                route = Route.TopHeadlineScreenNewsSources,
                arguments = listOf(navArgument("newsId") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getString("newsId") ?: ""
                TopHeadlineRoute(
                    newsId = newsId,
                    navHostController = navController,
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    })
            }

            composable(
                route = Route.TopHeadlineScreenNewsByCountry,
                arguments = listOf(navArgument("country") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val country = backStackEntry.arguments?.getString("country") ?: ""
                TopHeadlineRoute(
                    country = country,
                    navHostController = navController,
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    })
            }

            composable(
                route = Route.TopHeadlineScreenNewsByLanguage,
                arguments = listOf(navArgument("language") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val language = backStackEntry.arguments?.getString("language") ?: ""
                TopHeadlineRoute(
                    language = language,
                    navHostController = navController,
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    })
            }
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}



