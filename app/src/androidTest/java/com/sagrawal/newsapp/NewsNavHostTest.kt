package com.sagrawal.newsapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sagrawal.newsapp.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test

class NewsNavHostTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationToTopHeadlines() {
        // Assuming "Headlines" is one of your tabBarItems
        val headlinesTabTitle = "Headlines"

        // Perform the click on the "Headlines" tab
        composeTestRule.onNodeWithText(headlinesTabTitle).performClick()
        composeTestRule.onNodeWithText("Latest News").assertExists()
    }

    @Test
    fun testNavigationToNewsSources() {
        // Assuming "Headlines" is one of your tabBarItems
        val sourcesTitle = "Sources"

        // Perform the click on the "Headlines" tab
        composeTestRule.onNodeWithText(sourcesTitle).performClick()
        composeTestRule.onNodeWithText("News Sources").assertExists()
    }

    @Test
    fun testNavigationToCountries() {
        // Assuming "Headlines" is one of your tabBarItems
        val sourcesTitle = "Countries"

        // Perform the click on the "Headlines" tab
        composeTestRule.onNodeWithText(sourcesTitle).performClick()
        composeTestRule.onNodeWithText("Countries List").assertExists()
    }

    @Test
    fun testNavigationToLanguages() {
        // Assuming "Headlines" is one of your tabBarItems
        val sourcesTitle = "Languages"

        // Perform the click on the "Headlines" tab
        composeTestRule.onNodeWithText(sourcesTitle).performClick()
        composeTestRule.onNodeWithText("Language List").assertExists()
    }

    @Test
    fun testNavigationToSearch() {
        // Assuming "Headlines" is one of your tabBarItems
        val sourcesTitle = "Search"

        // Perform the click on the "Headlines" tab
        composeTestRule.onNodeWithText(sourcesTitle).performClick()
        composeTestRule.onNodeWithText("Search News").assertExists()
    }
}
