package com.sagrawal.newsapp.domain.repository

import com.sagrawal.newsapp.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    fun getCountries(): Flow<List<Country>>
}