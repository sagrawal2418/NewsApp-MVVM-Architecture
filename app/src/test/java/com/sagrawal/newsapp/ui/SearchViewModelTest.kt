package com.sagrawal.newsapp.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.model.Source
import com.sagrawal.newsapp.data.repository.SearchSourcesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.search.SearchViewModel
import com.sagrawal.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.sagrawal.newsapp.utils.DispatcherProvider
import com.sagrawal.newsapp.utils.TestDispatcherProvider
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
    private lateinit var searchSourcesRepository: SearchSourcesRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        viewModel = SearchViewModel(searchSourcesRepository, dispatcherProvider)
    }

    @Test
    fun `searchNews() with valid query updates uiState with success`() {
        // Mock the repository to return a list of articles

        runTest {
            val source = Source(id = "sourceId", name = "sourceName")
            val article = Article(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "urlToImage",
                source = source
            )
            val articles = listOf(article)

            // Perform the action
            viewModel.searchNews("abcd")

            // Mock the repository response
            `when`(searchSourcesRepository.getNewsSourcesByQueries("abcd")).thenReturn(
                flowOf(
                    articles
                )
            )

            viewModel.uiState.test {
                assertEquals(UiState.Success(articles), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }



            // Advance time to account for debounce
            advanceTimeBy(DEBOUNCE_TIMEOUT)

            verify(searchSourcesRepository, times(1)).getNewsSourcesByQueries("abcd")


//            // Assert the expected state is emitted
//            assert(items.any { it is UiState.Success && it.data == articles })
//
//            job.cancel()
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}