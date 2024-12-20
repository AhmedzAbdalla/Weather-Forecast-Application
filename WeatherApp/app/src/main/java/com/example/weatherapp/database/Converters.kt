package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.model.forecast.Alert
import com.example.weatherapp.model.forecast.Current
import com.example.weatherapp.model.forecast.Daily
import com.example.weatherapp.model.forecast.Hourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime?): String {
        return dateTime.toString()
    }
    @TypeConverter
    fun toDateTime(dateString: String): LocalDateTime? {
        if (dateString=="null"){
            return null
        }
        return LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun fromCurrent(current: Current): String {
        return Gson().toJson(current)
    }
    @TypeConverter
    fun toCurrent(json: String): Current {
        return Gson().fromJson(json, Current::class.java)
    }

    @TypeConverter
    fun fromAlerts(alert: List<Alert>?): String {
        return Gson().toJson(alert)
    }
    @TypeConverter
    fun toAlerts(json: String): List<Alert>? {
        return try {
            Gson().fromJson<List<Alert>>(json)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromDaily(daily: List<Daily>): String {
        return Gson().toJson(daily)
    }
    @TypeConverter
    fun toDaily(json: String): List<Daily> {
        return try {
            Gson().fromJson<List<Daily>>(json)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromHourly(hourly: List<Hourly>): String {
        return Gson().toJson(hourly)
    }
    @TypeConverter
    fun toHourly(json: String): List<Hourly> {
        return try {
            Gson().fromJson<List<Hourly>>(json)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)
}
