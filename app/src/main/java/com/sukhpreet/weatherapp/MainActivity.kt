package com.sukhpreet.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sukhpreet.weatherapp.ui.theme.WeatherappTheme
import com.sukhpreet.weatherapp.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherappTheme {
                HomeScreen()
            }
        }
    }
}