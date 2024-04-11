package com.sagrawal.newsapp.presentation.data

import app.cash.turbine.test
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.local.AppDatabaseService
import com.sagrawal.newsapp.data.repository.TopHeadlineRepositoryImpl
import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.ApiSource
import com.sagrawal.newsapp.domain.model.Article
import com.sagrawal.newsapp.domain.model.Source
import com.sagrawal.newsapp.domain.model.TopHeadlinesResponse
import com.sagrawal.newsapp.domain.model.toArticleEntity
import com.sagrawal.newsapp.domain.repository.TopHeadlineRepository
import com.sagrawal.newsapp.presentation.base.UiState
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyList
import org.mockito.Mockito.anyString
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    @Mock
    private lateinit var databaseService: AppDatabaseService

    private lateinit var topHeadlineRepository: TopHeadlineRepository

    @Before
    fun setUp() {
        topHeadlineRepository = TopHeadlineRepositoryImpl(databaseService, networkService)
    }

    @Test
    fun getTopHeadlines_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService).getTopHeadlines(COUNTRY)

            topHeadlineRepository.getTopHeadlines(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun getNewsBySources_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
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
            ).`when`(networkService).getNewsBySources(anyString())
            doReturn(flowOf(articles)).`when`(databaseService).getArticles()

            // When
            val result = topHeadlineRepository.getNewsBySources(anyString())

            // Then
            assertEquals(articles, result.toList()[0])
            verify(networkService).getNewsBySources(anyString())
            verify(databaseService).deleteAllAndInsertAll(anyList())
            verify(databaseService).getArticles()
        }
    }

    @Test
    fun getNewsBySources_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService).getNewsBySources(COUNTRY)

            topHeadlineRepository.getNewsBySources(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getNewsBySources(COUNTRY)
        }
    }

    @Test
    fun getNewsByLanguage_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
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
            ).`when`(networkService).getNewsByLanguage(anyString())
            doReturn(flowOf(articles)).`when`(databaseService).getArticles()

            // When
            val result = topHeadlineRepository.getNewsByLanguage(anyString())

            // Then
            assertEquals(articles, result.toList()[0])
            verify(networkService).getNewsByLanguage(anyString())
            verify(databaseService).deleteAllAndInsertAll(anyList())
            verify(databaseService).getArticles()
        }
    }

    @Test
    fun getNewsByLanguage_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService)
                .getNewsByLanguage(anyString())

            topHeadlineRepository.getNewsByLanguage(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getNewsByLanguage(anyString())
        }
    }

    @Test
    fun getTopHeadlines_should_return_articles_from_network_and_database() {
        runBlocking {
            // Given
            val country = "us"
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
            ).`when`(networkService).getTopHeadlines(country)
            doReturn(flowOf(articles)).`when`(databaseService).getArticles()

            // When
            val result = topHeadlineRepository.getTopHeadlines(country)

            // Then
            assertEquals(articles, result.toList()[0])
            verify(networkService).getTopHeadlines(country)
            verify(databaseService).deleteAllAndInsertAll(anyList())
            verify(databaseService).getArticles()
        }
    }

}