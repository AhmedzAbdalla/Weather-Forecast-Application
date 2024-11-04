package com.example.weatherapp.model.forecast

import androidx.room.Entity
import kotlinx.coroutines.flow.FlowCollector

@Entity(tableName = "forecast_table", primaryKeys = ["lat","lon"])
data class ForecastModel(
    val lat: Double,
    val lon: Double,
    var timezone: String,
    val timezone_offset: Double,
    val current: Current?,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    var isCurrent:Boolean= false,
    var isFavorite:Boolean = false,
    val alerts: List<Alert>? = null,
) : FlowCollector<List<ForecastModel>> {
    override suspend fun emit(value: List<ForecastModel>) {
        TODO("Not yet implemented")
    }
}