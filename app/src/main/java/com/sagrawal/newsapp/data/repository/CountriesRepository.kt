package com.sagrawal.newsapp.data.repository

import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor() {

    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(AppConstant.COUNTRIES)
        }
    }
}