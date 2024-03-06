package com.sagrawal.newsapp.presentation

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sagrawal.newsapp.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tabBarNavigation_selectsCorrectTab() {
        // Assert the first tab is selected initially
        composeTestRule
            .onNodeWithText("Headlines")
            .assertIsSelected()

        // Perform a click on the "Search" tab and assert it's now selected
        composeTestRule
            .onNodeWithText("Search")
            .performClick()
            .assertIsSelected()
    }
}
