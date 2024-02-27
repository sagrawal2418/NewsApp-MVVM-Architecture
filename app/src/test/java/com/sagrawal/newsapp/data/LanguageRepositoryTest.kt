package com.sagrawal.newsapp.data

import app.cash.turbine.test
import com.sagrawal.newsapp.data.model.Article
import com.sagrawal.newsapp.data.model.Language
import com.sagrawal.newsapp.data.model.Source
import com.sagrawal.newsapp.data.model.TopHeadlinesResponse
import com.sagrawal.newsapp.data.network.NetworkService
import com.sagrawal.newsapp.data.repository.LanguagesRepository
import com.sagrawal.newsapp.data.repository.TopHeadlineRepository
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class LanguageRepositoryTest {

    private lateinit var languagesRepository: LanguagesRepository

    @Before
    fun setUp() {
        languagesRepository = LanguagesRepository()
    }

    @Test
    fun `getLanguages emits expected languages list`() = runTest {
        // Act
        val result = languagesRepository.getLanguages().first()

        // Assert
        assertEquals(AppConstant.LANGUAGES, result)
    }
}