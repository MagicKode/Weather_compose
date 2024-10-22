package com.example.weatherapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapi.screens.MainCard
import com.example.weatherapi.screens.TabLayout
import com.example.weatherapi.ui.theme.WeatherApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApiTheme {
                Image(
                    painter = painterResource(id = R.drawable.weather_bg),
                    contentDescription = "image1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.8f),
                    contentScale = ContentScale.FillBounds  // чтобы фон растянулся на весь экран без обрезания
                )
                Column {
                    MainCard()
                    TabLayout()
                }
            }
        }
    }
}