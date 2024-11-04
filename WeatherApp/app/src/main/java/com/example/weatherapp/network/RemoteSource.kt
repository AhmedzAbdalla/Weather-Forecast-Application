package com.example.weatherapp.network

import com.example.weatherapp.model.forecast.ForecastModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface RemoteSource {
    suspend fun getForecast(lat:Double = 30.44,lon:Double = -94.04,lang:String = "ar"): Flow<Response<ForecastModel>>
}