package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.util.ListUtil
import com.sagrawal.newsapp.domain.model.Country
import com.sagrawal.newsapp.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor() : CountriesRepository {

    override fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(ListUtil.COUNTRIES)
        }
    }
}