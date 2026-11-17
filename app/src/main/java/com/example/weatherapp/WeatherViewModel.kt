package com.example.weatherapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var weather by mutableStateOf<WeatherResponse?>(null)
        private set
    var locationName by mutableStateOf("Getting location...")
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var myLocationQuery by mutableStateOf<String?>(null)
        private set
    var myLocationName by mutableStateOf<String?>(null)
        private set

    private fun updateWeather(result: WeatherResponse) {
        weather = result
        locationName = "${result.location.name}, ${result.location.region}"
        errorMessage = null
    }

    fun loadWeather(query: String) {
        viewModelScope.launch {
            try {
                updateWeather(WeatherApi.service.getForecast(BuildConfig.WEATHER_API_KEY, query, 3))
            } catch (e: Exception) {
                errorMessage = "Could not load weather: ${e.message}"
            }
        }
    }

    fun loadMyLocation(lat: Double, lon: Double) {
        val query = "$lat,$lon"
        myLocationQuery = query
        viewModelScope.launch {
            try {
                val result = WeatherApi.service.getForecast(BuildConfig.WEATHER_API_KEY, query, 3)
                myLocationName = "${result.location.name}, ${result.location.region}"
                if (weather == null) updateWeather(result)
            } catch (e: Exception) {
                errorMessage = "Could not load weather: ${e.message}"
            }
        }
    }

    fun showError(message: String) {
        errorMessage = message
    }
}