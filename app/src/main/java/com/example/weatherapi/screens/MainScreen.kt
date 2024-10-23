package com.example.weatherapi.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi.R
import com.example.weatherapi.ui.theme.BlueLight
import com.example.weatherapi.data.WeatherModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainCard() {
    //Основной контейнер Column, гед будут карточки и всё остальное
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp, // чтобы карточка не поднималась вверх (тень)
            shape = RoundedCornerShape(10.dp)
        ) {
            //Контейнер для размещения в нём элементов по вертикали
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "20 Jun 2022 13:00",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )

                    AsyncImage( //для загрузки фото с сервера
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "image2",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 3.dp, end = 8.dp)
                    )

                }
                Text(
                    text = "Minsk",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White
                )
                Text(
                    text = "23°C",
                    style = TextStyle(fontSize = 65.sp),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {

                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "image3",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "23°C/12°C",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    IconButton(onClick = {

                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "image4",
                            tint = Color.White
                        )
                    }

                }

            }
        }
    }
}

/**
 * функция для контейнера с Табами
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun TabLayout(daysList: MutableState<List<WeatherModel>>) {
    val tabList = listOf("HOURS", "DAYS")  //список табов (кнопок) для переключения
    val pagerState = rememberPagerState()  // для сохранения состояния
    val tabIndex = pagerState.currentPage  // сохранения состояния при запуске, открыта сстраница
    val coroutineScope = rememberCoroutineScope()  // для запуска анимации переключения
    Column(
        modifier = Modifier
            .padding(
                start = 5.dp,
                end = 5.dp
            )
            .clip(
                RoundedCornerShape(5.dp)
            )
    ) {
        TabRow(  //контейнер для кнопок и табов, которые подсвечиваются индликатором. Специальная панелька
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = BlueLight,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = text)
                    }
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->     // указывает какая страница открыта
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    daysList.value
                ) { _, item ->
                    ListItem(item) //при запуске берутся items из WeatherModel по очереди и срздаётся список ListItem
                }
            }
        }
    }
}