package com.sagrawal.newsapp.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.NewsSource
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.data.repository.NewsSourcesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.countries.CountriesViewModel
import com.sagrawal.newsapp.ui.newssources.NewsSourcesViewModel
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
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsSourcesViewModelTest {

    @Mock
    private lateinit var newsSourcesRepository: NewsSourcesRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<NewsSource>()))
                .`when`(newsSourcesRepository)
                .getNewsSources(AppConstant.COUNTRY)
            val viewModel = NewsSourcesViewModel(newsSourcesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<NewsSource>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsSourcesRepository, times(1)).getNewsSources(AppConstant.COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            val errorMessage = "Error Message For You"
            doReturn(flow<List<NewsSource>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(newsSourcesRepository)
                .getNewsSources(AppConstant.COUNTRY)

            val viewModel = NewsSourcesViewModel(newsSourcesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsSourcesRepository, times(1)).getNewsSources(AppConstant.COUNTRY)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}