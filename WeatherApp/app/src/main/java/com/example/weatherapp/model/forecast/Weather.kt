package com.example.weatherapp.model.forecast

//import android.util.Log
//import com.giraffe.weatherforecasapplication.R

data class Weather(
    val description: String,
    val icon: String,
    val id: Double,
    val main: String
)