package com.example.weatherapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.weatherapi.screens.MainCard
import com.example.weatherapi.screens.TabLayout
import com.example.weatherapi.ui.theme.WeatherApiTheme

const val API_KEY = "02e11a1c270b436e988143921241610"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApiTheme {
                getData("Minsk", this)
                Image(
                    painter = painterResource(id = R.drawable.weather_bg),
                    contentDescription = "image1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
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

//ф-я получения данных от сервера погоды  При помощи библ Volley
/**
 * запрос отправляется на сервер
 * сервер проверяет наш API_KEY
 * в каком городе мы хотим получить ингфо
 * какие параметры
 */
private fun getData(city: String, context: Context) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=${API_KEY}" +
            "&q=${city}" +
            "&days=" +
            "3" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)   //создаём запрос-очередь, для получения ответа
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
            response ->  //это и есть responsebody
            Log.d("package:mine", "Response: $response")
        },
        {
            Log.d("package:mine", "VolleyError: $it")
        }
    )
    queue.add(sRequest)  // создаём очередь запроса
}
