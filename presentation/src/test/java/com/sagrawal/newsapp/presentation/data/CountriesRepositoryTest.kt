package com.sagrawal.newsapp.presentation.data

import com.sagrawal.newsapp.data.repository.CountriesRepositoryImpl
import com.sagrawal.newsapp.data.util.ListUtil.COUNTRIES
import com.sagrawal.newsapp.domain.repository.CountriesRepository
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
class CountriesRepositoryTest {

    private lateinit var countriesRepository: CountriesRepository

    @Before
    fun setUp() {
        countriesRepository = CountriesRepositoryImpl()
    }

    @Test
    fun `getCountries emits expected languages list`() = runTest {
        // Act
        val result = countriesRepository.getCountries().first()

        // Assert
        assertEquals(COUNTRIES, result)
    }
}