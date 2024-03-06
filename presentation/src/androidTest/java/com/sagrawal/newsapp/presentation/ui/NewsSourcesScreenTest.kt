package com.sagrawal.newsapp.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.newssources.NewsSourcesScreen
import org.junit.Rule
import org.junit.Test

class NewsSourcesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            NewsSourcesScreen(
                uiState = UiState.Loading,
                null,
                onNewsClick = {})
        }
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun articles_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            NewsSourcesScreen(uiState = UiState.Success(dummyNewsSources), null, onNewsClick = {})
        }

        composeTestRule
            .onNodeWithText(
                dummyNewsSources[0].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    dummyNewsSources[5].name,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                dummyNewsSources[5].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenUiStateIsError_isShown() {
        val errorMessage = "Something went wrong"

        composeTestRule.setContent {
            NewsSourcesScreen(
                uiState = UiState.Error(errorMessage), null,
                onNewsClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}

private val dummyNewsSources: List<NewsSource> = listOf(
    NewsSource(
        id = "1",
        name = "Source One",
        description = "Description of Source One",
        url = "https://sourceone.com",
        category = "General",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "2",
        name = "Source Two",
        description = "Description of Source Two",
        url = "https://sourcetwo.com",
        category = "Technology",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "3",
        name = "Source Three",
        description = "Description of Source Three",
        url = "https://sourcethree.com",
        category = "Sports",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "4",
        name = "Source Four",
        description = "Description of Source Four",
        url = "https://sourcefour.com",
        category = "Business",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "5",
        name = "Source Five",
        description = "Description of Source Five",
        url = "https://sourcefive.com",
        category = "Entertainment",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "6",
        name = "Source Six",
        description = "Description of Source Six",
        url = "https://sourcesix.com",
        category = "Health",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "7",
        name = "Source Seven",
        description = "Description of Source Seven",
        url = "https://sourceseven.com",
        category = "Science",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "8",
        name = "Source Eight",
        description = "Description of Source Eight",
        url = "https://sourceeight.com",
        category = "General",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "9",
        name = "Source Nine",
        description = "Description of Source Nine",
        url = "https://sourcenine.com",
        category = "Technology",
        language = "en",
        country = "US"
    ),
    NewsSource(
        id = "10",
        name = "Source Ten",
        description = "Description of Source Ten",
        url = "https://sourceten.com",
        category = "Sports",
        language = "en",
        country = "US"
    )
)

