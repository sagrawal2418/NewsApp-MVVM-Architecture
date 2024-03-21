package com.sagrawal.newsapp.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.countries.CountriesRoute
import com.sagrawal.newsapp.presentation.languages.LanguageRoute
import com.sagrawal.newsapp.presentation.main.TabBarItem
import com.sagrawal.newsapp.presentation.main.TabView
import com.sagrawal.newsapp.presentation.newssources.NewsSourcesRoute
import com.sagrawal.newsapp.presentation.search.SearchRoute
import com.sagrawal.newsapp.presentation.topheadline.TopHeadlineRoute
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.AppConstant.NAV_ARG_NEWS_ID

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
        NavHost(
            navController = navController,
            startDestination = stringResource(id = R.string.headlines)
        ) {
            composable(AppConstant.HEADLINES) {
                TopHeadlineRoute(onNewsClick = {
                    openCustomChromeTab(context, it)
                })
            }

            composable(AppConstant.SOURCES) {
                NewsSourcesRoute(navController)
            }
            composable(AppConstant.COUNTRIES) {
                CountriesRoute(navController)
            }
            composable(AppConstant.LANGUAGES) {
                LanguageRoute(navController)
            }
            composable(AppConstant.SEARCH) {
                SearchRoute(onNewsClick = {
                    openCustomChromeTab(context, it)
                })
            }

            composable(
                route = Route.TopHeadlineScreenNewsSources,
                arguments = listOf(navArgument(NAV_ARG_NEWS_ID) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getString(NAV_ARG_NEWS_ID) ?: ""
                TopHeadlineRoute(
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    })
            }

            composable(
                route = Route.TopHeadlineScreenNewsByCountry,
                arguments = listOf(navArgument(AppConstant.NAV_ARG_COUNTRY) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val country = backStackEntry.arguments?.getString(AppConstant.NAV_ARG_COUNTRY) ?: ""
                TopHeadlineRoute(
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    })
            }

            composable(
                route = Route.TopHeadlineScreenNewsByLanguage,
                arguments = listOf(navArgument(AppConstant.NAV_ARG_LANGUAGE) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val language =
                    backStackEntry.arguments?.getString(AppConstant.NAV_ARG_LANGUAGE) ?: ""
                TopHeadlineRoute(
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



