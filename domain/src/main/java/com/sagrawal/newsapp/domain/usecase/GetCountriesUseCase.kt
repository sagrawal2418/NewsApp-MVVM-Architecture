package com.sagrawal.newsapp.domain.usecase

import com.sagrawal.newsapp.domain.model.Country
import com.sagrawal.newsapp.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val countriesRepository: CountriesRepository) {

    operator fun invoke(): Flow<List<Country>> {
        return countriesRepository.getCountries()
    }

}