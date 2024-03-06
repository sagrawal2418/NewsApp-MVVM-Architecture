package com.sagrawal.newsapp.presentation.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.domain.model.Country
import com.sagrawal.newsapp.domain.usecase.GetCountriesUseCase
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.presentation.countries.CountriesViewModel
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.presentation.utils.TestDispatcherProvider
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
class CountriesViewModelTest {

    @Mock
    private lateinit var useCase: GetCountriesUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchCountries_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<Country>()))
                .`when`(useCase)
                .invoke()
            val viewModel = CountriesViewModel(useCase, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Country>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke()
        }
    }

    @Test
    fun fetchCountries_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Country>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(useCase)
                .invoke()

            val viewModel = CountriesViewModel(useCase, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke()
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}