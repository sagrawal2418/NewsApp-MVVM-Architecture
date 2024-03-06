package com.sagrawal.newsapp.presentation.data

import com.sagrawal.newsapp.data.repository.LanguagesRepositoryImpl
import com.sagrawal.newsapp.data.util.ListUtil.LANGUAGES
import com.sagrawal.newsapp.domain.repository.LanguagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LanguageRepositoryTest {

    private lateinit var languagesRepository: LanguagesRepository

    @Before
    fun setUp() {
        languagesRepository = LanguagesRepositoryImpl()
    }

    @Test
    fun `getLanguages emits expected languages list`() = runTest {
        // Act
        val result = languagesRepository.getLanguages().first()

        // Assert
        assertEquals(LANGUAGES, result)
    }
}