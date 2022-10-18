package com.composeapp.sampleandroidtask.composables

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.AppClass.Companion.instance
import com.composeapp.sampleandroidtask.Constants.Companion.SEARCHED_CITY_NAME
import com.composeapp.sampleandroidtask.Constants.Companion.apiCallSearchWeatherLiveData
import com.composeapp.sampleandroidtask.Constants.Companion.favouriteCitiesList
import com.composeapp.sampleandroidtask.Constants.Companion.weatherSearchUpdateLiveData
import com.composeapp.sampleandroidtask.R
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.utils.openLoaderDialogue
import com.composeapp.sampleandroidtask.utils.writeToCSV
import kotlinx.coroutines.launch

@Composable
fun searchWeatherContent()
{
    val coroutineScope = rememberCoroutineScope()
    val isShowDialogue = remember{ mutableStateOf(false) }
    val searchText = remember{ mutableStateOf(String()) }
    Column(
        content = {
            TextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it
                    SEARCHED_CITY_NAME = it

                    if(it.isNullOrBlank() || it.isNullOrEmpty())
                    {
                        weatherSearchUpdateLiveData.value = Any()
                    }
                },
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(15.dp, 15.dp, 15.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        border = BorderStroke(2.dp, color = Color.Black),
                        shape = RoundedCornerShape(5.dp)
                    ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.text_search_hint),
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            ClickableText(
                AnnotatedString(stringResource(R.string.text_add_to_favourite)) ,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .wrapContentHeight()
                    .wrapContentWidth(),
                onClick = {
                    if(!SEARCHED_CITY_NAME.isNullOrEmpty() || !SEARCHED_CITY_NAME.isNullOrBlank()) {
                        coroutineScope.launch {
                            favouriteCitiesList.add(SEARCHED_CITY_NAME)
                            writeToCSV()
                        }
                    }
                    else{
                        Toast.makeText(instance, instance.getString(R.string.enter_valid_name),Toast.LENGTH_LONG).show()
                    }
                }
            )

            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                ),
                onClick = {

                    if(!SEARCHED_CITY_NAME.isNullOrEmpty() || !SEARCHED_CITY_NAME.isNullOrBlank()) {
                        isShowDialogue.value = true
                        apiCallSearchWeatherLiveData.value = searchText.value
                    }
                    else{
                        Toast.makeText(instance, instance.getString(R.string.enter_valid_name),Toast.LENGTH_LONG).show()
                    }
                },
                content = {
                    Text(
                        text = stringResource(R.string.search),
                        color = Color.White,
                        fontSize = 13.sp
                    )
                },
                modifier = Modifier
                    .padding(15.dp, 7.dp, 15.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(15.dp)
            )

            if(isShowDialogue.value)
            {
                openLoaderDialogue()
            }

            weatherSearchUpdateLiveData.observeAsState().value?.let { data->
                isShowDialogue.value = false
                when(data)
                {
                    is Base -> setupCurrentWeatherAndMoreOptions(data)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 0.dp, 0.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
}