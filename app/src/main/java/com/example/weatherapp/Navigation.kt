package com.example.weatherapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    var selectedTab by remember { mutableStateOf(0) }
    val weatherViewModel: WeatherViewModel = viewModel()
    val requestLocation = rememberLocationRequester(weatherViewModel)

    LaunchedEffect(Unit) {
        if (weatherViewModel.weather == null) requestLocation()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(weatherViewModel.locationName) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.Cloud, contentDescription = "Current weather") },
                    label = { Text("Current Weather") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.CalendarViewWeek, contentDescription = "Daily forecast") },
                    label = { Text("Daily Forecast") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { innerPadding ->
        val weather = weatherViewModel.weather
        val contentModifier = Modifier.padding(innerPadding)

        when {
            weather == null -> Box(
                modifier = contentModifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val error = weatherViewModel.errorMessage
                if (error != null) Text(error) else CircularProgressIndicator()
            }
            selectedTab == 0 -> CurrentWeather(weather, contentModifier)
            else -> Forecast(weather, contentModifier)
        }
    }
}