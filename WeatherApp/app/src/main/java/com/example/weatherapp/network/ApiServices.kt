package com.example.weatherapp.network

import com.example.weatherapp.model.forecast.ForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("data/3.0/onecall")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
    ):Response<ForecastModel>
}
