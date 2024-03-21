package com.sagrawal.newsapp.presentation.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.search.SearchBar
import com.sagrawal.newsapp.presentation.search.SearchContent
import com.sagrawal.newsapp.presentation.TestUtils.testApiArticles
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBarTest() {
        // Track if onQuerySubmitted was called with the correct value
        var wasCalledWithCorrectValue = false
        val testQuery = "Hello, World!"

        composeTestRule.setContent {
            SearchBar(onQuerySubmitted = { query ->
                wasCalledWithCorrectValue = query == testQuery
            })
        }

        // Find the text field and input the test query
        composeTestRule
            .onNodeWithText("Search News")
            .performTextInput(testQuery)

        // Perform the IME action which should trigger onQuerySubmitted
        composeTestRule
            .onNodeWithText("Search News")
            .performImeAction()

        // Assert that onQuerySubmitted was called with the correct value
        assertTrue("onQuerySubmitted was not called with the correct query", wasCalledWithCorrectValue)
    }

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            SearchContent(
                uiState = UiState.Loading,
                null,
                onNewsClick = {})
        }
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun articles_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            SearchContent(uiState = UiState.Success(testApiArticles), null, onNewsClick = {})
        }

        composeTestRule
            .onNodeWithText(
                testApiArticles[0].title,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    testApiArticles[5].title,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                testApiArticles[5].title,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenUiStateIsError_isShown() {
        val errorMessage = "Something went wrong"

        composeTestRule.setContent {
            SearchContent(
                uiState = UiState.Error(errorMessage), null,
                onNewsClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}
