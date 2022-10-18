package com.composeapp.sampleandroidtask.composables

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.AppClass
import com.composeapp.sampleandroidtask.AppClass.Companion.instance
import com.composeapp.sampleandroidtask.Constants
import com.composeapp.sampleandroidtask.Constants.Companion.PAGER_INDEX
import com.composeapp.sampleandroidtask.R
import com.composeapp.sampleandroidtask.activities.ActivityFiveDaysWeatherData
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.utils.KeyUtils

@Composable
fun setupCurrentWeatherAndMoreOptions(base: Base) {
    Column(
        content = {
            showWeatherForecast(base)

            ClickableText(
                AnnotatedString(stringResource(R.string.text_show_five_days_data)),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                onClick = {
                    instance.startActivity(
                        Intent(
                            AppClass.instance,
                            ActivityFiveDaysWeatherData::class.java
                        ).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            when(PAGER_INDEX)
                            {
                                0-> putExtra(KeyUtils.NAME, Constants.CURRENT_CITY_NAME)
                                1-> putExtra(KeyUtils.NAME, Constants.SEARCHED_CITY_NAME)
                                2-> putExtra(KeyUtils.NAME, Constants.FAVOURITE_CITY_NAME)
                            }
                        })
                }
            )
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    )
}