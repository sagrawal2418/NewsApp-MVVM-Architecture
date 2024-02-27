package com.sagrawal.newsapp.ui

import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.ui.base.UiState
import com.sagrawal.newsapp.ui.countries.CountriesViewModel
import com.sagrawal.newsapp.ui.languages.LanguagesViewModel
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
class LanguageViewModelTest {

    @Mock
    private lateinit var languagesRepository: LanguagesRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchLanguages_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {

            doReturn(flowOf(emptyList<Language>()))
                .`when`(languagesRepository)
                .getLanguages()
            val viewModel = LanguagesViewModel(languagesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Language>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(languagesRepository, times(1)).getLanguages()
        }
    }

    @Test
    fun fetchLanguages_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Country>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(languagesRepository)
                .getLanguages()

            val viewModel = LanguagesViewModel(languagesRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(languagesRepository, times(1)).getLanguages()
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}