//package com.sagrawal.newsapp.presentation.ui
//
//import androidx.activity.ComponentActivity
//import androidx.compose.ui.test.assertHasClickAction
//import androidx.compose.ui.test.hasScrollToNodeAction
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performScrollToNode
//import com.sagrawal.newsapp.data.util.ListUtil.COUNTRIES
//import com.sagrawal.newsapp.domain.model.Country
//import com.sagrawal.newsapp.presentation.R
//import com.sagrawal.newsapp.presentation.base.UiState
//import com.sagrawal.newsapp.presentation.countries.CountriesScreen
//import com.sagrawal.newsapp.presentation.countries.CountriesViewModel
//import io.mockk.every
//import io.mockk.mockk
//import kotlinx.coroutines.flow.MutableStateFlow
//import org.junit.Rule
//import org.junit.Test
//
//class CountriesScreenTest {
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    private val mockViewModel = mockk<CountriesViewModel>(relaxed = true)
//
//    @Test
//    fun loading_whenUiStateIsLoading_isShown() {
//        composeTestRule.setContent {
//
//            CountriesScreen(
//                uiState = UiState.Loading,
//                mockViewModel,
//                onCountryClick = {})
//        }
//        composeTestRule.onNodeWithContentDescription(
//            composeTestRule.activity.resources.getString(
//                R.string.loading
//            )
//        )
//            .assertExists()
//    }
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
//}