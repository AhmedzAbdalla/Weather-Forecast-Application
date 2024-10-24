package com.example.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("data/2.5/onecall")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
    ):Response<ForecastModel>
}