package com.sagrawal.newsapp.presentation.data

import app.cash.turbine.test
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.local.AppDatabaseService
import com.sagrawal.newsapp.data.repository.SearchSourcesRepositoryImpl
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.ApiSource
import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.model.Source
import com.sagrawal.newsapp.domain.model.TopHeadlinesResponse
import com.sagrawal.newsapp.domain.repository.SearchSourcesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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

    @Mock
    private lateinit var databaseService: AppDatabaseService

    private lateinit var searchSourcesRepository: SearchSourcesRepository

    @Before
    fun setUp() {
        searchSourcesRepository = SearchSourcesRepositoryImpl(databaseService, networkService)
    }

    @Test
    fun getNewsSourcesByQueries_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runBlocking {

            val apiArticles = listOf(
                ApiArticle(
                    "title",
                    "description",
                    "url",
                    "urlToImage",
                    apiSource = ApiSource("id", "name")
                )
            )
            val articles = listOf(
                Article(
                    "title",
                    "description",
                    "url",
                    "urlToImage",
                    "imageUrl",
                    Source("id", "name")
                )
            )

            doReturn(
                TopHeadlinesResponse(
                    status = "ok",
                    totalResults = 1,
                    apiArticles = apiArticles
                )
            ).`when`(networkService).getNewsByQueries(anyString())
            doReturn(flowOf(articles)).`when`(databaseService).getArticles()

            // When
            val result = searchSourcesRepository.getNewsSourcesByQueries(anyString())

            // Then
            assertEquals(articles, result.toList()[0])
            verify(networkService).getNewsByQueries(anyString())
            verify(databaseService).deleteAllAndInsertAll(Mockito.anyList())
            verify(databaseService).getArticles()
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