package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrentWeather(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Cloud,
            contentDescription = "Weather condition",
            modifier = Modifier.size(120.dp)
        )
        Text(text = PlaceholderData.CITY, fontSize = 28.sp)
        Text(text = "${PlaceholderData.TEMPERATURE}°", fontSize = 72.sp)
        Text(text = PlaceholderData.CONDITION, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Feels like ${PlaceholderData.FEELS_LIKE}°", fontSize = 16.sp)
        Text(text = "Humidity ${PlaceholderData.HUMIDITY}%", fontSize = 16.sp)
        Text(text = "Wind ${PlaceholderData.WIND} km/h", fontSize = 16.sp)
        Text(text = "Precipitation ${PlaceholderData.PRECIPITATION}%", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherPreview() {
    CurrentWeather()
}