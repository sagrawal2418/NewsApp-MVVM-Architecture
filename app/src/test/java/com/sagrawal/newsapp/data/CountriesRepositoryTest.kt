package com.sagrawal.newsapp.data

import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.utils.AppConstant
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
        countriesRepository = CountriesRepository()
    }

    @Test
    fun `getCountries emits expected languages list`() = runTest {
        // Act
        val result = countriesRepository.getCountries().first()

        // Assert
        assertEquals(AppConstant.COUNTRIES, result)
    }
}