package com.example.weatherapi.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi.ui.theme.BlueLight
import com.example.weatherapi.data.WeatherModel

@Composable
fun ListItem(item: WeatherModel) {  //аналог CardView для RecyclerView
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        backgroundColor = BlueLight,
        shape = RoundedCornerShape(5.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
            ) {
                Text(text = item.time)
                Text(
                    text = item.condition,
                    color = Color.White
                )
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage( //для загрузки фото с сервера
                model = "https:" + item.icon,  //передаём ссылку иконки от сервера по ссылке
                contentDescription = "image5",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}
