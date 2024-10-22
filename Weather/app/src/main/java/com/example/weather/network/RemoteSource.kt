package com.example.weather

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteSource {
    suspend fun getForecast(lat:Double,lon:Double,lang:String = "ar"): Flow<Response<ForecastModel>>
}