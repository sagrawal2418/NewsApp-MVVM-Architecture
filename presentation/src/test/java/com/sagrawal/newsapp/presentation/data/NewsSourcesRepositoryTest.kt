package com.sagrawal.newsapp.presentation.data

import app.cash.turbine.test
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.repository.NewsSourcesRepositoryImpl
import com.sagrawal.newsapp.domain.model.NewsSource
import com.sagrawal.newsapp.domain.model.NewsSourceResponse
import com.sagrawal.newsapp.domain.repository.NewsSourcesRepository
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsSourcesRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var newsSourcesRepository: NewsSourcesRepository

    @Before
    fun setUp() {
        newsSourcesRepository = NewsSourcesRepositoryImpl(networkService)
    }

    @Test
    fun getNewsSources_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val newsSource = NewsSource(
                id = "sourceId",
                name = "sourceName",
                url = "url",
                description = "News source",
                category = "category",
                language = "english",
                country = "usa"
            )

            val newsSourceList = mutableListOf<NewsSource>()
            newsSourceList.add(newsSource)

            val newsSourceResponse = NewsSourceResponse(
                status = "ok", sources = newsSourceList
            )

            doReturn(newsSourceResponse).`when`(networkService).getNewsSources(COUNTRY)

            newsSourcesRepository.getNewsSources(COUNTRY).test {
                assertEquals(newsSourceResponse.sources, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(networkService, times(1)).getNewsSources(COUNTRY)
        }
    }

    @Test
    fun getNewsSources_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService).getNewsSources(COUNTRY)

            newsSourcesRepository.getNewsSources(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getNewsSources(COUNTRY)
        }
    }

}