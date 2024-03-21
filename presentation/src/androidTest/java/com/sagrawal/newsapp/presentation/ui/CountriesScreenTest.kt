package com.sagrawal.newsapp.presentation.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.platform.app.InstrumentationRegistry
import com.sagrawal.newsapp.presentation.R
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.countries.CountriesScreen
import org.junit.Rule
import org.junit.Test

class CountriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {

            CountriesScreen(
                uiState = UiState.Loading,
                null,
                onCountryClick = {})
        }

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.onNodeWithContentDescription(
            context.resources.getString(
                R.string.loading
            )
        )
            .assertExists()
    }
//
//    @Test
//    fun countries_whenUiStateIsSuccess_isShown() {
//        composeTestRule.setContent {
//            // Mock the behavior of the ViewModel
//            every { mockViewModel.uiState } returns MutableStateFlow(
//                UiState.Success(
//                    listOf(
//                        Country("Country 1", "URL 1"),
//                        Country("Country 2", "URL 2")
//                    )
//                )
//            )
//            CountriesScreen(
//                uiState = UiState.Success(COUNTRIES),
//                mockViewModel,
//                onCountryClick = {})
//        }
//
//        composeTestRule
//            .onNodeWithText(
//                COUNTRIES[0].name,
//                substring = true
//            )
//            .assertExists()
//            .assertHasClickAction()
//
//        composeTestRule.onNode(hasScrollToNodeAction())
//            .performScrollToNode(
//                hasText(
//                    COUNTRIES[17].name,
//                    substring = true
//                )
//            )
//
//        composeTestRule
//            .onNodeWithText(
//                COUNTRIES[14].name,
//                substring = true
//            )
//            .assertExists()
//            .assertHasClickAction()
//            .performClick()
//
//    }
//
//    @Test
//    fun error_whenUiStateIsError_isShown() {
//        val errorMessage = "Something went wrong"
//
//        composeTestRule.setContent {
//            CountriesScreen(
//                uiState = UiState.Error(errorMessage), mockViewModel,
//                onCountryClick = {}
//            )
//        }
//
//        composeTestRule
//            .onNodeWithText(errorMessage)
//            .assertExists()
//    }
}