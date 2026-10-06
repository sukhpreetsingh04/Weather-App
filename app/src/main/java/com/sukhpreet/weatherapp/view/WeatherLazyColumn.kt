package com.sukhpreet.weatherapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sukhpreet.weatherapp.BuildConfig
import com.sukhpreet.weatherapp.view.uicomponents.`Segmented-button`
import com.sukhpreet.weatherapp.view.uicomponents.WeatherInfoCard
import com.sukhpreet.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherLazyColumn() {
    val viewModel: WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()
    var city by remember { mutableStateOf("") }
    var isFahrenheit by remember { mutableStateOf(false) }
    val apiKey = BuildConfig.OPENWEATHER_API_KEY

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("City") },
                    placeholder = { Text("Enter city name") },
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    `Segmented-button`(
                        isFahrenheit = isFahrenheit,
                        onUnitSelected = { isFahrenheit = it }
                    )

                    Button(
                        onClick = {
                            viewModel.fetchWeather(city, apiKey)
                        }
                    ) {
                        Text(text = "Check Weather")
                    }
                }
            }
        }

        item {
            weatherData?.let { weather ->
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        WeatherInfoCard(
                            label = "City",
                            value = weather.name,
                            modifier = Modifier.weight(1f)
                        )

                        WeatherInfoCard(
                            label = "Temperature",
                            value = buildString {
                                val temperature = if (isFahrenheit) {
                                    viewModel.celsiusToFahrenheit(weather.main.temp.toDouble())
                                } else {
                                    weather.main.temp.toDouble()
                                }
                                append("${"%.1f".format(temperature)}°")
                                append(if (isFahrenheit) "F" else "C")
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        WeatherInfoCard(
                            label = "Humidity",
                            value = "${weather.main.humidity}%",
                            modifier = Modifier.weight(1f)
                        )

                        WeatherInfoCard(
                            label = "Description",
                            value = weather.weather[0].description,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
