package com.example.weatherapp

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val location: LocationInfo,
    val current: Current,
    val forecast: ForecastData
)

data class LocationInfo(val name: String, val region: String, val country: String)

data class Condition(val text: String, val icon: String)

data class Current(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("precip_mm") val precipMm: Double,
    val humidity: Int,
    val condition: Condition
)

data class ForecastData(
    @SerializedName("forecastday") val forecastDays: List<ForecastDay>
)

data class ForecastDay(val date: String, val day: Day)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    @SerializedName("totalprecip_mm") val totalPrecipMm: Double,
    @SerializedName("avghumidity") val avgHumidity: Double,
    @SerializedName("daily_chance_of_rain") val chanceOfRain: Int,
    @SerializedName("daily_chance_of_snow") val chanceOfSnow: Int,
    val condition: Condition
)

data class SearchResult(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)

// The API gives "//cdn.weatherapi.com/.../64x64/..." — add https and use the bigger icon
fun iconUrl(icon: String): String = "https:" + icon.replace("64x64", "128x128")