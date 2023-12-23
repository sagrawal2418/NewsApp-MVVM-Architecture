package com.sagrawal.newsapp.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrawal.newsapp.data.model.Country
import com.sagrawal.newsapp.data.repository.CountriesRepository
import com.sagrawal.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val countries = countriesRepository.getCountries("countries.json")
                _uiState.value = UiState.Success(countries)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch countries")
            }
        }
    }

}