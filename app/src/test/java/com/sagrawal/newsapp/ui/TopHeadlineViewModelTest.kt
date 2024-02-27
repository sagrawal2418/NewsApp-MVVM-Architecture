package com.sagrawal.newsapp.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.topheadline.TopHeadlineViewModel
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.DispatcherProvider
import com.sagrawal.newsapp.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var topHeadlineRepository: TopHeadlineRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun loadTopHeadlines_newsSources_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedNewsId = "12345"
            `when`(savedStateHandle.get<String>("newsId")).thenReturn(expectedNewsId)

            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getNewsBySources(expectedNewsId)
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getNewsBySources(expectedNewsId)
        }
    }

    @Test
    fun loadTopHeadlines_newsSources_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedNewsId = "12345"
            `when`(savedStateHandle.get<String>("newsId")).thenReturn(expectedNewsId)

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlineRepository)
                .getNewsBySources(expectedNewsId)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getNewsBySources(expectedNewsId)
        }
    }

    @Test
    fun loadTopHeadlines_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getTopHeadlines(AppConstant.COUNTRY)
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlines(AppConstant.COUNTRY)
        }
    }

    @Test
    fun loadTopHeadlines_country_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("country")).thenReturn(expectedCountry)

            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getTopHeadlines(expectedCountry)
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlines(expectedCountry)
        }
    }

    @Test
    fun loadTopHeadlines_country_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("country")).thenReturn(expectedCountry)

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlineRepository)
                .getTopHeadlines(expectedCountry)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlines(expectedCountry)
        }
    }

    @Test
    fun loadTopHeadlines_language_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedLanguage = "abcde"
            `when`(savedStateHandle.get<String>("language")).thenReturn(expectedLanguage)

            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getNewsByLanguage(expectedLanguage)
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getNewsByLanguage(expectedLanguage)
        }
    }

    @Test
    fun loadTopHeadlines_language_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("language")).thenReturn(expectedCountry)

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlineRepository)
                .getNewsByLanguage(expectedCountry)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider, savedStateHandle)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getNewsByLanguage(expectedCountry)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}