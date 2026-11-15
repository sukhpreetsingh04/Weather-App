package com.sukhpreet.weatherapp.data.model

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val visibility: Int
)

data class Main(
    val temp: Float,
    val humidity: Int,
    val feelsLike: Float
)

data class Weather(
    val description: String
)