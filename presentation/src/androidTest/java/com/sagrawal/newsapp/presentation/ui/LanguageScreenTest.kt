package com.sagrawal.newsapp.presentation.ui

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.test.platform.app.InstrumentationRegistry
import com.sagrawal.newsapp.data.util.ListUtil.LANGUAGES
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.languages.LanguagesScreen
import org.junit.Rule
import org.junit.Test

class LanguageScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            LanguagesScreen(
                uiState = UiState.Loading,
                null,
                onLanguageClick = {})
        }
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun countries_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            LanguagesScreen(uiState = UiState.Success(LANGUAGES), null, onLanguageClick = {})
        }

        composeTestRule
            .onNodeWithText(
                LANGUAGES[0].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    LANGUAGES[10].name,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                LANGUAGES[10].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
            .performClick()

    }

    @Test
    fun error_whenUiStateIsError_isShown() {
        val errorMessage = "Something went wrong"

        composeTestRule.setContent {
            LanguagesScreen(
                uiState = UiState.Error(errorMessage), null,
                onLanguageClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

}