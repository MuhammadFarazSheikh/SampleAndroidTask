package com.composeapp.sampleandroidtask.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.composeapp.sampleandroidtask.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.composeapp.sampleandroidtask.AppClass.Companion.instance
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.CELCIUS
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.FAHRENHEIT

/*SHOW MESSAGE DIALOGUE*/
@Composable
fun openAlertForMessage(
    title: String,
    message: String,
    confirmButtonListener: () -> Unit,
    dimissButtonListener: () -> Unit
) {
    AlertDialog(
        backgroundColor = Color.White,
        title = {
            Text(
                title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                message,
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        onDismissRequest = { },
        confirmButton = {
            TextButton(
                onClick = {
                    confirmButtonListener.invoke()
                },
                content = {
                    Text(
                        stringResource(R.string.text_ok),
                        color = Color.Black,
                        fontSize = 11.sp
                    )
                }
            )
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dimissButtonListener.invoke()
                },
                content = {
                    Text(
                        stringResource(R.string.text_cancel),
                        color = Color.Black,
                        fontSize = 11.sp
                    )
                }
            )
        }
    )
}

/*OPEN CUSTOM DIALOGUE TO SHOW CONTENT LOADING*/
@Composable
fun openLoaderDialogue() {
    Dialog(onDismissRequest = { /*TODO*/ },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                content = {
                    Text(
                        stringResource(R.string.text_loading_label),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 25.dp, 0.dp, 0.dp)
                    )

                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 10.dp, 0.dp, 25.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        color = Color.Black,
                        strokeWidth = 2.dp
                    )
                }
            )
        }
    )
}

/*OPEN DIALOGUE WITH SETTINGS OPTION*/
@Composable
fun showSettingsDialogue(onCelciusClick:()->Unit,onFehrenheitClick:()->Unit) {
    var tempType = PreferenceDataStoreUtils.getTemperatureType(instance).collectAsState(initial = "")
    val celciusSelection = remember{ mutableStateOf(false) }
    val fahrenheitSelection = remember{ mutableStateOf(false) }
    if(!tempType.value.isNullOrEmpty() || !tempType.value.isNullOrBlank())
    {
        if(tempType.value.equals(CELCIUS))
        {
            celciusSelection.value = true
            fahrenheitSelection.value = false
        }else if(tempType.value.equals(FAHRENHEIT))
        {
            celciusSelection.value = false
            fahrenheitSelection.value = true
        }
    }
    else
    {
        celciusSelection.value = true
    }
    Dialog(
        onDismissRequest = { /*TODO*/ },
        content = {
            Column(
                content = {
                    Text(
                        stringResource(R.string.text_temp_type),
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            RadioButton(
                                selected = celciusSelection.value, onClick = {
                                    celciusSelection.value = true
                                    fahrenheitSelection.value = false
                                    onCelciusClick.invoke()
                                }
                            )
                            Text(
                                stringResource(R.string.text_celcius),
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .padding(5.dp, 0.dp, 0.dp, 0.dp),
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 15.dp, 0.dp, 0.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            RadioButton(
                                selected = fahrenheitSelection.value, onClick = {
                                    celciusSelection.value = false
                                    fahrenheitSelection.value = true
                                    onFehrenheitClick.invoke()
                                }
                            )
                            Text(
                                stringResource(R.string.text_fahrenheit),
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                    .padding(15.dp, 15.dp)
            )
        }
    )
}
