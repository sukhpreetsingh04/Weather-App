package com.sukhpreet.weatherapp.data.remote

import com.sukhpreet.weatherapp.data.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): WeatherResponse

    companion object{
        private const val BASE_URl = "https://api.openweathermap.org/data/2.5/"

        fun create(): WeatherApi{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URl)
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }
}