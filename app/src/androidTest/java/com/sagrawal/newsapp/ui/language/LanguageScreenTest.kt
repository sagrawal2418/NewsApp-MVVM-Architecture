package com.sagrawal.newsapp.ui.language

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.sagrawal.newsapp.R
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.languages.LanguagesScreen
import com.sagrawal.newsapp.utils.AppConstant
import org.junit.Rule
import org.junit.Test

class LanguageScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            LanguagesScreen(
                uiState = UiState.Loading,
                null,
                onLanguageClick = {})
        }
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun countries_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            LanguagesScreen(uiState = UiState.Success(AppConstant.LANGUAGES), null, onLanguageClick = {})
        }

        composeTestRule
            .onNodeWithText(
                AppConstant.LANGUAGES[0].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    AppConstant.LANGUAGES[10].name,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                AppConstant.LANGUAGES[10].name,
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