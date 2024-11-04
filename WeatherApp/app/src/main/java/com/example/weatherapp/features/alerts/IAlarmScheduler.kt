package com.example.weatherapp.features.alerts

import com.example.weatherapp.model.alert.AlertItem

interface IAlarmScheduler {
    fun schedule(item: AlertItem)
    fun cancel(item: AlertItem)
}