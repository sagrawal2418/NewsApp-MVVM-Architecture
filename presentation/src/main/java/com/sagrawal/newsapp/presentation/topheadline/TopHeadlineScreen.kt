package com.sagrawal.newsapp.presentation.topheadline

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.CustomTopAppBar
import com.sagrawal.newsapp.presentation.base.openCustomChromeTab
import com.sagrawal.newsapp.presentation.languages.LanguagesScreen
import com.sagrawal.newsapp.presentation.topheadline.network.NetworkTopHeadlineRoute
import com.sagrawal.newsapp.presentation.topheadline.offline.OfflineTopHeadlineRoute
import com.sagrawal.newsapp.presentation.topheadline.paging.PagingTopHeadlineRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineScreen() {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { TopHeadlineTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Scaffold(topBar =
    { CustomTopAppBar(title = stringResource(R.string.headlines)) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                TopHeadlineTabs.entries.forEachIndexed { index, currentTab ->
                    val selected = pagerState.currentPage == index
                    Tab(
                        selected = selected,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = {
                            Text(
                                text = stringResource(id = currentTab.text),
                                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.7f
                                )
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) currentTab.selectedIcon else currentTab.unselectedIcon,
                                contentDescription = "${currentTab.text} Tab",
                                tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.7f
                                )
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                val context = LocalContext.current
                when (TopHeadlineTabs.entries[page]) {
                    TopHeadlineTabs.Network -> NetworkTopHeadlineRoute(
                        onNewsClick = {
                            openCustomChromeTab(context, it)
                        })

                    TopHeadlineTabs.Offline -> OfflineTopHeadlineRoute(
                        onNewsClick = {
                            openCustomChromeTab(context, it)
                        })

                    TopHeadlineTabs.Paging -> PagingTopHeadlineRoute(
                        onNewsClick = {
                            openCustomChromeTab(context, it)
                        })
                }
            }
        }
    }
}

enum class TopHeadlineTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val text: Int
) {

    Network(
        unselectedIcon = Icons.Outlined.AddCircle,
        selectedIcon = Icons.Filled.AddCircle,
        text = R.string.network_topheadlines
    ),
    Offline(
        unselectedIcon = Icons.Outlined.Warning,
        selectedIcon = Icons.Filled.Warning,
        text = R.string.offline_topheadlines
    ),
    Paging(
        unselectedIcon = Icons.Outlined.Menu,
        selectedIcon = Icons.Filled.Menu,
        text = R.string.paging_topheadlines
    )
}