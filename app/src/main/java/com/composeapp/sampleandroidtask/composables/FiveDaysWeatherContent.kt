package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composeapp.sampleandroidtask.models.Base

/*SHOW WEATHER DATA FOR NEXT FIVE DAYS WITH THREE HOURS WEATHER DATA FOR EACH DAY*/
@Composable
fun showNextFiveDaysWeather(list: ArrayList<Base>)
{
    Column(
        content = {
            list.forEach { baseBO ->
                showWeatherForecast(base = baseBO)

                Divider(
                    thickness = 1.dp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp, 10.dp, 5.dp, 0.dp)
                )
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight()
    )
}