package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlin.math.roundToInt

@Composable
fun CurrentWeather(weather: WeatherResponse, modifier: Modifier = Modifier) {
    val current = weather.current
    val today = weather.forecast.forecastDays.first().day
    val chance = maxOf(today.chanceOfRain, today.chanceOfSnow)

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = iconUrl(current.condition.icon),
            contentDescription = current.condition.text,
            modifier = Modifier.size(140.dp)
        )
        Text(text = current.condition.text, fontSize = 22.sp)
        Text(text = "${current.tempC.roundToInt()}°", fontSize = 72.sp)
        Text(text = "Feels like ${current.feelsLikeC.roundToInt()}°", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Wind ${current.windDir} ${current.windKph.roundToInt()} km/h", fontSize = 16.sp)
        Text(text = "Precipitation $chance% · ${current.precipMm} mm", fontSize = 16.sp)
        Text(text = "Humidity ${current.humidity}%", fontSize = 16.sp)
    }
}