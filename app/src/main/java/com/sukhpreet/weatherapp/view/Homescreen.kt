package com.sukhpreet.weatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sukhpreet.weatherapp.BuildConfig
import com.sukhpreet.weatherapp.ui.theme.WeatherappTheme
import com.sukhpreet.weatherapp.view.uicomponents.`Segmented-button`
import com.sukhpreet.weatherapp.view.uicomponents.WeatherInfoCard
import com.sukhpreet.weatherapp.viewmodel.WeatherViewModel
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.Column

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()
    var city by remember { mutableStateOf("") }
    val apiKey = BuildConfig.OPENWEATHER_API_KEY

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Weather App", color = MaterialTheme.colorScheme.primary) },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Menu, contentDescription = "Menu Icon"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }, content = { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("City")
                        },
                        placeholder = {
                            Text("Enter city name")
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        `Segmented-button`()

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

            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                weatherData?.let {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        WeatherInfoCard(
                            label = "City",
                            value = it.name,
                            modifier = Modifier.weight(1f)
                        )

                        WeatherInfoCard(
                            label = "Temperature",
                            value = "${it.main.temp}°C",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        WeatherInfoCard(
                            label = "Humidity",
                            value = "${it.main.humidity}%",
                            modifier = Modifier.weight(1f)
                        )

                        WeatherInfoCard(
                            label = "Description",
                            value = it.weather[0].description,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    WeatherappTheme {
        HomeScreen()
    }
}