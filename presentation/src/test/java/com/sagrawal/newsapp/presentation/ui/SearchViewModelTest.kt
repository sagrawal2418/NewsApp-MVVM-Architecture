package com.sagrawal.newsapp.presentation.ui

import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesByQueriesUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.search.SearchViewModel
import com.sagrawal.newsapp.presentation.utils.TestDispatcherProvider
import com.sagrawal.newsapp.util.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Mock
    private lateinit var useCase: GetNewsSourcesByQueriesUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        viewModel = SearchViewModel(useCase, dispatcherProvider)
    }

    @Test
    fun `searchNews() updates query`() {
        // Given
        val query = "Test Query"

        // When
        viewModel.searchNews(query)

        // Then
        assert(viewModel.query.value == query)
    }

    @Test
    fun `createNewsFlow() handles empty search query`() = runBlockingTest {
        // Given
        val emptyQuery = ""

        // When
        viewModel.searchNews(emptyQuery)

        // Then
        delay(100) // Wait for debounce timeout
        //  `verify`(useCase, never()).invoke(any())
        assert(viewModel.uiState.value is UiState.Success) // UI state should remain success with empty list
    }

}