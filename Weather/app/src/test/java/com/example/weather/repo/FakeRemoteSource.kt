package com.example.weather


import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FakeRemoteSource(private val forecast: ForecastModel) : RemoteSource {
    override suspend fun getForecast(
        lat: Double,
        lon: Double,
        lang: String
    ): Flow<Response<ForecastModel>> {
        TODO("Not yet implemented")
    }

}
