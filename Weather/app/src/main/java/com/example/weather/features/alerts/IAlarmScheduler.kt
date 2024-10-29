package com.example.weather

import com.example.weather.AlertItem


interface IAlarmScheduler {
    fun schedule(item: AlertItem)
    fun cancel(item: AlertItem)
}