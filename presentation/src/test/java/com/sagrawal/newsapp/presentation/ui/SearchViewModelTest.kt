package com.sagrawal.newsapp.presentation.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.model.Source
import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesByQueriesUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.search.SearchViewModel
import com.sagrawal.newsapp.presentation.utils.TestDispatcherProvider
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
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

//    @Test
//    fun `searchNews() with valid query updates uiState with success`() {
//        // Mock the repository to return a list of articles
//
//        runTest {
//            val source = Source(id = "sourceId", name = "sourceName")
//            val article = Article(
//                title = "title",
//                description = "description",
//                url = "url",
//                imageUrl = "urlToImage",
//                source = source
//            )
//            val articles = listOf(article)
//
//            // Perform the action
//            viewModel.searchNews("abcd")
//
//            // Mock the repository response
//            `when`(useCase.invoke("abcd")).thenReturn(
//                flowOf(
//                    articles
//                )
//            )
//
//            viewModel.uiState.test {
//                assertEquals(UiState.Success(articles), awaitItem())
//                cancelAndIgnoreRemainingEvents()
//            }
//
//
//
//            // Advance time to account for debounce
//            advanceTimeBy(DEBOUNCE_TIMEOUT)
//
//            verify(useCase, times(1)).invoke("abcd")
//        }
//    }

    @Test
    fun `searchNews() with valid query updates uiState with success`() = runTest {
//        // Arrange
//        val source = Source(id = "sourceId", name = "sourceName")
//        val article = Article(
//            title = "title",
//            description = "description",
//            url = "url",
//            imageUrl = "urlToImage",
//            source = source
//        )
//        val articles = listOf(article)
//
//        // Mock the use case response before performing the action
//        `when`(useCase.invoke("abcd")).thenReturn(flowOf(articles))
//
//// Now perform the action
//        viewModel.searchNews("abcd")
//
//// Then advance the time to simulate the debounce
//        advanceTimeBy(DEBOUNCE_TIMEOUT)
//
////        // Stub the useCase response before triggering the action
////        `when`(useCase.invoke("abcd")).thenReturn(flowOf(articles))
////
////        // Act
////        viewModel.searchNews("abcd")
////
////        // Advance time by debounce timeout to simulate debounce behavior
////        advanceTimeBy(DEBOUNCE_TIMEOUT)
//
//        // Assert
//        viewModel.uiState.test {
//            // Advance time after subscribing to the flow
//            awaitItem() // Initial state
//            advanceTimeBy(DEBOUNCE_TIMEOUT)
//            assertEquals(UiState.Success(articles), awaitItem())
//            cancelAndIgnoreRemainingEvents()
//        }
//
//        verify(useCase, times(1)).invoke("abcd")
    }


    @After
    fun tearDown() {
        // do something if required
    }

}