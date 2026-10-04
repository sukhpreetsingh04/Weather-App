package com.sukhpreet.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukhpreet.weatherapp.data.model.WeatherResponse
import com.sukhpreet.weatherapp.data.remote.WeatherApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val weatherApi = WeatherApi.create()

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(city, apiKey)
                _weatherData.value = response

                println("WEATHER SUCCESS: $response")

            } catch (e: Exception) {
                println("WEATHER ERROR: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}