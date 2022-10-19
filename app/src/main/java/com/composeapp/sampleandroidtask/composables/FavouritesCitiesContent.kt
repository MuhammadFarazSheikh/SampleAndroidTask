package com.composeapp.sampleandroidtask.composables

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.AppClass
import com.composeapp.sampleandroidtask.Constants.Companion.FAVOURITE_CITY_NAME
import com.composeapp.sampleandroidtask.Constants.Companion.favouriteCitiesList
import com.composeapp.sampleandroidtask.activities.ActivityFavouriteCityWeather
import com.composeapp.sampleandroidtask.utils.KeyUtils

/*SHOW FAVOURITES CITIES LIST*/
@Composable
fun showFavouriteCities() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        content = {
            favouriteCitiesList?.let { list ->
                items(count = list.size)
                { index ->
                    ClickableText(
                        text = AnnotatedString(list.get(index = index)),
                        onClick = {
                            FAVOURITE_CITY_NAME = list.get(index)
                            AppClass.instance.startActivity(
                                Intent(
                                    AppClass.instance,
                                    ActivityFavouriteCityWeather::class.java
                                ).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    putExtra(KeyUtils.NAME, list.get(index))
                                })
                        },
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp, 10.dp, 10.dp, 0.dp),

                        )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp, 10.dp, 5.dp, 0.dp),
                        color = Color.DarkGray,
                        thickness = 0.8.dp
                    )
                }
            }
        })
}