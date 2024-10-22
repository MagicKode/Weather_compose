package com.example.weatherapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherapi.screens.MainScreen
import com.example.weatherapi.ui.theme.WeatherApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApiTheme {
               MainScreen()
            }
        }
    }
}