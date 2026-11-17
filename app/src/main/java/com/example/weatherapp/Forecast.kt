package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun Forecast(weather: WeatherResponse, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(weather.forecast.forecastDays) { forecastDay ->
            val day = forecastDay.day
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = iconUrl(day.condition.icon),
                        contentDescription = day.condition.text,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(formatDate(forecastDay.date), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(day.condition.text)
                        Text("High ${day.maxTempC.roundToInt()}° / Low ${day.minTempC.roundToInt()}°")
                        Text("Max wind ${day.maxWindKph.roundToInt()} km/h")
                        Text("Precipitation ${maxOf(day.chanceOfRain, day.chanceOfSnow)}% · ${day.totalPrecipMm} mm")
                        Text("Humidity ${day.avgHumidity.roundToInt()}%")
                    }
                }
            }
        }
    }
}

fun formatDate(date: String): String {
    val parsed = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
    return SimpleDateFormat("EEEE, MMM d", Locale.getDefault()).format(parsed!!)
}