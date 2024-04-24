package com.sagrawal.newsapp.presentation.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNetworkTopHeadlineUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNewsByLanguageUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.GetNewsBySourceUseCase
import com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline.NetworkTopHeadlineUseCases
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.topheadline.network.NetworkTopHeadlineViewModel
import com.sagrawal.newsapp.presentation.utils.TestDispatcherProvider
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.util.NetworkHelper
import com.sagrawal.newsapp.utils.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NetworkTopHeadlineViewModelTest {

    @Mock
    private lateinit var useCases: NetworkTopHeadlineUseCases

    @Mock
    private lateinit var getTopHeadlineUseCase: GetNetworkTopHeadlineUseCase

    @Mock
    private lateinit var getNewsByLanguageUseCase: GetNewsByLanguageUseCase

    @Mock
    private lateinit var getNewsBySourceUseCase: GetNewsBySourceUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: NetworkTopHeadlineViewModel

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        viewModel = NetworkTopHeadlineViewModel(
            useCases,
            dispatcherProvider,
            savedStateHandle
        )
        // Mock the use case within the TopHeadlineUseCases container
        `when`(useCases.getTopHeadlineUseCase).thenReturn(getTopHeadlineUseCase)
        `when`(useCases.getNewsByLanguageUseCase).thenReturn(getNewsByLanguageUseCase)
        `when`(useCases.getNewsBySourceUseCase).thenReturn(getNewsBySourceUseCase)
    }

    @Test
    fun loadTopHeadlines_newsSources_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runBlocking {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedNewsId = "12345"
            `when`(savedStateHandle.get<String>("newsId")).thenReturn(expectedNewsId)

            doAnswer {
                flowOf(emptyList<Article>())
            }.`when`(getNewsBySourceUseCase).invoke(expectedNewsId)

            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(getNewsBySourceUseCase, times(1)).invoke(expectedNewsId)
        }
    }

    @Test
    fun loadTopHeadlines_newsSources_whenRepositoryResponseError_shouldSetErrorUiState() {

        runBlocking {
            val newsId = "abcde"
            `when`(savedStateHandle.get<String>("newsId")).thenReturn(newsId)

            val errorMessage = "Error Message For You"
            // Adjust the mocking strategy for a suspend function returning a flow
            doAnswer {
                flow<List<Article>> { throw IllegalStateException(errorMessage) }
            }.`when`(getNewsBySourceUseCase).invoke(newsId)

            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getNewsBySourceUseCase, times(1)).invoke(newsId)
        }
    }

    @Test
    fun loadTopHeadlines_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runBlocking {

            doReturn(flowOf(emptyList<Article>()))
                .`when`(getTopHeadlineUseCase)
                .invoke(AppConstant.COUNTRY)
            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(getTopHeadlineUseCase, times(1)).invoke(AppConstant.COUNTRY)
        }
    }

    @Test
    fun loadTopHeadlines_country_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runBlocking {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("country")).thenReturn(expectedCountry)

            doReturn(flowOf(emptyList<Article>()))
                .`when`(getTopHeadlineUseCase)
                .invoke(expectedCountry)
            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(getTopHeadlineUseCase, times(1)).invoke(expectedCountry)
        }
    }

    @Test
    fun loadTopHeadlines_country_whenRepositoryResponseError_shouldSetErrorUiState() {
        runBlocking {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("country")).thenReturn(expectedCountry)

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(getTopHeadlineUseCase)
                .invoke(expectedCountry)

            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getTopHeadlineUseCase, times(1)).invoke(expectedCountry)
        }
    }

    @Test
    fun loadTopHeadlines_language_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runBlocking {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedLanguage = "abcde"
            `when`(savedStateHandle.get<String>("language")).thenReturn(expectedLanguage)

            doReturn(flowOf(emptyList<Article>()))
                .`when`(getNewsByLanguageUseCase)
                .invoke(expectedLanguage)
            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(getNewsByLanguageUseCase, times(1)).invoke(expectedLanguage)
        }
    }

    @Test
    fun loadTopHeadlines_language_whenRepositoryResponseError_shouldSetErrorUiState() {
        runBlocking {

            // Suppose "newsId" is the key used to retrieve the ID from SavedStateHandle
            val expectedCountry = "abcde"
            `when`(savedStateHandle.get<String>("language")).thenReturn(expectedCountry)

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(getNewsByLanguageUseCase)
                .invoke(expectedCountry)

            val viewModel =
                NetworkTopHeadlineViewModel(
                    useCases,
                    dispatcherProvider,
                    savedStateHandle
                )
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getNewsByLanguageUseCase, times(1)).invoke(expectedCountry)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}