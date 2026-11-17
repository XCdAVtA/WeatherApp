package com.example.weatherapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("days") days: Int
    ): WeatherResponse

    @GET("search.json")
    suspend fun searchCities(
        @Query("key") key: String,
        @Query("q") query: String
    ): List<SearchResult>
}

object WeatherApi {
    val service: WeatherApiService = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApiService::class.java)
}