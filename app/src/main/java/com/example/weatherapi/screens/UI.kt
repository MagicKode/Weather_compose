package com.example.weatherapi.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi.ui.theme.BlueLight
import com.example.weatherapi.data.WeatherModel

/**
 * Функция для получения информации по ДНЯМ, при нажатии на таб, либо прогноз по ЧАСАМ
 */
@Composable
fun MainList(list: List<WeatherModel>, currentDay: MutableState<WeatherModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            ListItem(
                item,
                currentDay
            ) //при запуске берутся items из WeatherModel по очереди и срздаётся список ListItem
        }
    }

}

@Composable      //аналог CardView для RecyclerView
fun ListItem(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable  //если прогноз по часам, то при нажатии ничего не происходит
                currentDay.value =
                    item  //при нажатии, передаём WeatherModel, день, который я выбрал.
            },
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

/**
 * создаёт всплывающий диалог для кнопки поиска
 */
@Composable
fun DialogSearch(dialogState: MutableState<Boolean>, onSubmit: (String) -> Unit) {  //передаём функцию для обновления города при поиске
    val dialogText = remember {
        mutableStateOf("") // Параметр окна вода
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false  //при нажатии вокруг диалога, он скрывается
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
            }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Введите название города:")
                TextField(              //текстовое поля для ввода
                    value = dialogText.value,
                    onValueChange = {    // параметр перерисовывания  / изменения, при вводе текста
                        dialogText.value = it   //передаём текст (значение)
                    }
                )
            }
        }
    )
}
