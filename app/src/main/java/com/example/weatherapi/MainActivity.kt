package com.example.weatherapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.weatherapi.data.WeatherModel
import org.json.JSONObject

const val API_KEY = "02e11a1c270b436e988143921241610"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApiTheme {
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                val currentDay = remember {
                    mutableStateOf(
                        WeatherModel(
                            "",
                            "",
                            "0.0", //инфа по умолчанию начальная, чтобы не передовать Пустую строку, иначе Exception вылетает
                            "",
                            "",
                            "0.0",
                            "0.0",
                            ""
                        )
                    )
                }
                getData("Minsk", this, daysList, currentDay)
                Image(
                    painter = painterResource(id = R.drawable.weather_bg),
                    contentDescription = "image1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds  // чтобы фон растянулся на весь экран без обрезания
                )
                Column {
                    MainCard(currentDay)
                    TabLayout(daysList, currentDay)
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
private fun getData(
    city: String, context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=${API_KEY}" +
            "&q=${city}" +
            "&days=" +
            "3" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)   //создаём запрос-очередь, для получения ответа
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->  //это и есть responsebody
            val list = getWeatherByDay(response)
            currentDay.value = list[0]  //на 0 позицию списка запишется СЕГОДНЯШНИЙ день с сервера
            daysList.value = list
        },
        {
            Log.d("package:mine", "VolleyError: $it")
        }
    )
    queue.add(sRequest)  // создаём очередь запроса
}

/**
 * функция получения списка параметров погоды за ДЕНЬ через JSONObject
 * Циклом перебираем дни и получаем параметры каждого в соответствии с WeatherModel
 */
private fun getWeatherByDay(response: String): List<WeatherModel> {
    if (response.isEmpty()) return listOf()
    val list = ArrayList<WeatherModel>()
    val mainObject =
        JSONObject(response)  //создаём объект формата JSON, т.к. мы запрашиваем в данном формате

    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

    for (i in 0 until days.length()) { //цикл перебора дней
        val item = days[i] as JSONObject  //кастим к JSONObject, т.к. возвращает не в таком формате
        list.add(
            WeatherModel(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day").getJSONObject("condition").getString("text"),
                item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(                       //изменяем элементы копии модели для текущего дня
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")
    )                                              //копия перезапишется на 0 позицию для Текущего дня.
    return list
}
