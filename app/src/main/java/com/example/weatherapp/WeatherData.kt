package com.example.weatherapp

data class DailyForecast(
    val day: String,
    val condition: String,
    val high: Int,
    val low: Int
)

object PlaceholderData {
    const val CITY = "Halifax, NS"
    const val TEMPERATURE = 14
    const val CONDITION = "Cloudy"
    const val FEELS_LIKE = 13
    const val PRECIPITATION = 11
    const val HUMIDITY = 97
    const val WIND = 11

    val forecast = listOf(
        DailyForecast("Monday", "Sunny", 21, 12),
        DailyForecast("Tuesday", "Cloudy", 19, 11),
        DailyForecast("Wednesday", "Rain", 15, 9),
        DailyForecast("Thursday", "Cloudy", 12, 8),
        DailyForecast("Friday", "Sunny", 20, 13)
    )
}