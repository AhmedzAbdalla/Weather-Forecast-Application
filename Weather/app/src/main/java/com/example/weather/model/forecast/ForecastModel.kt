package com.example.weather

import androidx.room.Entity

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
    //val minutely: List<Minutely>,
)