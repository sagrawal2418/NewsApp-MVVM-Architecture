package com.sagrawal.newsapp.presentation.data

import app.cash.turbine.test
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.repository.SearchSourcesRepositoryImpl
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.ApiSource
import com.sagrawal.newsapp.domain.model.TopHeadlinesResponse
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchSourcesRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var searchSourcesRepository: SearchSourcesRepository

    @Before
    fun setUp() {
        searchSourcesRepository = SearchSourcesRepositoryImpl(networkService)
    }

    @Test
    fun getNewsSourcesByQueries_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val source = ApiSource(id = "sourceId", name = "sourceName")
            val article = ApiArticle(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "urlToImage",
                apiSource = source
            )

            val articles = mutableListOf<ApiArticle>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, apiArticles = articles
            )

            doReturn(topHeadlinesResponse).`when`(networkService).getNewsByQueries(anyString())

            searchSourcesRepository.getNewsSourcesByQueries(anyString()).test {
                assertEquals(topHeadlinesResponse.apiArticles, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(networkService, times(1)).getNewsByQueries(anyString())
        }
    }

    @Test
    fun getNewsSourcesByQueries_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService)
                .getNewsByQueries(anyString())

            searchSourcesRepository.getNewsSourcesByQueries(anyString()).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getNewsByQueries(anyString())
        }
    }
}