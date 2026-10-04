# 🌤️ Weather App

A simple Android weather application built with **Kotlin and Jetpack Compose**.  
The app allows users to search for weather information by city and displays the
retrieved weather data through a clean Material 3 interface.

This project is being developed as a practical Android project to understand
modern Android development concepts including **Jetpack Compose, Material 3,
Retrofit, REST APIs, ViewModel, StateFlow, Coroutines, and API integration**.

## ✨ Features

- 🌍 Search weather by city name
- 🌡️ Display current temperature
- 💧 Display humidity
- ☁️ Display current weather conditions
- 🌐 Fetch weather data using the OpenWeather API
- 🎨 Material 3 based UI
- 🃏 Reusable weather information cards
- 🔄 Reactive UI updates using `StateFlow`
- 🔐 API key configured through `local.properties`
- 📱 Modern Android UI built entirely with Jetpack Compose

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Retrofit**
- **OpenWeather API**
- **ViewModel**
- **StateFlow**
- **Kotlin Coroutines**
- **Gradle Kotlin DSL**

## 🏗️ Project Structure

The project follows a layered structure to keep API handling, data models,
UI components, theming, and state management separated.

```text
com.sukhpreet.weatherapp
│
├── data
│   ├── model
│   │   └── WeatherResponse.kt
│   │
│   └── remote
│       └── WeatherApi.kt
│
├── ui
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── view
│   ├── uicomponents
│   │
│   └── Homescreen.kt
│
├── viewmodel
│   └── WeatherViewModel.kt
│
└── MainActivity.kt
````

### 📂 Folder Responsibilities

| Package             | Responsibility                                                     |
| ------------------- | ------------------------------------------------------------------ |
| `data/model`        | Contains Kotlin data classes representing the weather API response |
| `data/remote`       | Handles the Retrofit API interface and remote weather requests     |
| `ui/theme`          | Contains Material 3 colors, typography, and application theme      |
| `view/uicomponents` | Contains reusable Compose UI components                            |
| `view`              | Contains the main weather screen and UI composition                |
| `viewmodel`         | Handles weather-related state and API request logic                |
| `MainActivity.kt`   | Application entry point                                            |

## 🔄 Data Flow

```text
User
 ↓
HomeScreen
 ↓
WeatherViewModel
 ↓
WeatherApi
 ↓
OpenWeather API
 ↓
WeatherResponse
 ↓
StateFlow
 ↓
HomeScreen
```

The `WeatherViewModel` manages the weather data using `MutableStateFlow`
and exposes the state to the Compose UI through `StateFlow`.

This keeps the API/request logic separate from the UI while allowing the
Compose interface to react automatically when the weather data changes.

## 🔑 API Key

The OpenWeather API key is stored locally using `local.properties` rather than
being directly hardcoded into the Kotlin source code.

The API key is passed through the Gradle configuration and accessed by the
application when making the weather request.

> `local.properties` should not be committed to Git.

## 🎨 UI

The UI is built using **Jetpack Compose and Material 3**.

Current UI components include:

* Material 3 `OutlinedTextField`
* Material 3 `Button`
* Material 3 `ElevatedCard`
* Material 3 typography and color scheme
* `LazyColumn` for displaying content
* Reusable weather information components

## 📚 What This Project Demonstrates

This project focuses on understanding how the different layers of a modern
Android application communicate with each other.

In particular, it demonstrates:

* Building UI with Jetpack Compose
* Managing UI state with ViewModel and StateFlow
* Making REST API requests using Retrofit
* Working with Kotlin Coroutines
* Converting API responses into Kotlin data classes
* Separating API, model, ViewModel, and UI responsibilities
* Using Material 3 for application theming
* Keeping API configuration outside the source code

## 🚧 Project Status

The core weather functionality is currently implemented.

The project is still under development, with additional improvements and
features planned as development continues.
