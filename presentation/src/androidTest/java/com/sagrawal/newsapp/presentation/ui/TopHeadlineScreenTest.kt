//package com.sagrawal.newsapp.presentation.ui
//
//import androidx.compose.ui.test.assertHasClickAction
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.hasScrollToNodeAction
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performScrollToNode
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.platform.app.InstrumentationRegistry
//import com.sagrawal.newsapp.presentation.R
//import com.sagrawal.newsapp.presentation.base.UiState
//import com.sagrawal.newsapp.presentation.topheadline.TopHeadlineScreen
//import com.sagrawal.newsapp.presentation.TestUtils.testApiArticles
//import org.junit.Rule
//import org.junit.Test
//import java.lang.Thread.sleep
//
//class TopHeadlineScreenTest {
//
//    @get:Rule
//    val activityScenarioRule = ActivityScenarioRule(TopHeadlineActivity::class.java)
//
//    @Test
//    fun testClickOnNetworkHeadlineButtonNavigatesToNetworkTopHeadlineScreen() {
//        // Find the button with text "Network Top Headlines"
//        Espresso.onView(ViewMatchers.withId(R.id.button_text)) // Replace with actual button ID if available
//            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.network_topheadlines)))
//
//        // Simulate a click on the button
//        Espresso.onView(ViewMatchers.withId(R.id.button_text)) // Replace with actual button ID if available
//            .perform(ViewActions.click())
//
//        // Verification logic - Ideally, you'd have a way to check for navigation completion (might involve custom logic)
//        // In this example, we'll just check if the button text remains the same (not ideal)
//        Espresso.onView(ViewMatchers.withId(R.id.button_text)) // Replace with actual button ID if available
//            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.network_topheadlines)))
//    }
//}
//
////    @Test
////    fun loading_whenUiStateIsLoading_isShown() {
////        composeTestRule.setContent {
////            TopHeadlineScreen(
////                uiState = UiState.Loading,
////                null,
////                onNewsClick = {})
////        }
////        val context = InstrumentationRegistry.getInstrumentation().targetContext
////
////        composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.loading))
////            .assertExists()
////    }
////
////    @Test
////    fun articles_whenUiStateIsSuccess_isShown() {
////        composeTestRule.setContent {
////            TopHeadlineScreen(uiState = UiState.Success(testApiArticles), null, onNewsClick = {})
////        }
////
////        composeTestRule
////            .onNodeWithText(
////                testApiArticles[0].title,
////                substring = true
////            )
////            .assertExists()
////            .assertHasClickAction()
////
////        composeTestRule.onNode(hasScrollToNodeAction())
////            .performScrollToNode(
////                hasText(
////                    testApiArticles[5].title,
////                    substring = true
////                )
////            )
////
////        composeTestRule
////            .onNodeWithText(
////                testApiArticles[5].title,
////                substring = true
////            )
////            .assertExists()
////            .assertHasClickAction()
////    }
////
////    @Test
////    fun error_whenUiStateIsError_isShown() {
////        val errorMessage = "Something went wrong"
////
////        composeTestRule.setContent {
////            TopHeadlineScreen(
////                uiState = UiState.Error(errorMessage), null,
////                onNewsClick = {}
////            )
////        }
////
////        composeTestRule
////            .onNodeWithText(errorMessage)
////            .assertExists()
////    }
//
