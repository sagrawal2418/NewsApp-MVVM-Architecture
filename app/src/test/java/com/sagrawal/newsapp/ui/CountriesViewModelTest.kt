package com.sagrawal.newsapp.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.countries.CountriesViewModel
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
class CountriesViewModelTest {

    @Mock
    private lateinit var countriesRepository: CountriesRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchCountries_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<Country>()))
                .`when`(countriesRepository)
                .getCountries()
            val viewModel = CountriesViewModel(countriesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Country>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(countriesRepository, times(1)).getCountries()
        }
    }

    @Test
    fun fetchCountries_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Country>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(countriesRepository)
                .getCountries()

            val viewModel = CountriesViewModel(countriesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(countriesRepository, times(1)).getCountries()
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}