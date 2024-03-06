package com.sagrawal.newsapp.presentation.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.domain.usecase.GetNewsSourcesUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.newssources.NewsSourcesViewModel
import com.sagrawal.newsapp.presentation.utils.TestDispatcherProvider
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.utils.AppConstant
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
    private lateinit var useCase: GetNewsSourcesUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<NewsSource>()))
                .`when`(useCase)
                .invoke(AppConstant.COUNTRY)
            val viewModel = NewsSourcesViewModel(useCase, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<NewsSource>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke(AppConstant.COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            val errorMessage = "Error Message For You"
            doReturn(flow<List<NewsSource>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(useCase)
                .invoke(AppConstant.COUNTRY)

            val viewModel = NewsSourcesViewModel(useCase, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke(AppConstant.COUNTRY)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}