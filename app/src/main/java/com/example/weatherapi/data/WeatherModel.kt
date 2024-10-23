package com.example.weatherapicompose.data

data class WeatherModel(  //основные параметры погоды, из response body
    val city: String,
    val time: String,
    val currentTemp: String,
    val condition: String,
    val icon: String,
    val maxTemp: String,
    val minTemp:String,
    val hours: String
)
