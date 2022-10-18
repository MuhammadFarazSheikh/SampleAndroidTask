package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.R

@Composable
fun drawerContent(buttonOpenSettings:()->Unit)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        content = {

            Text(
                stringResource(R.string.text_label),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp, 10.dp, 5.dp, 0.dp),
                color = Color.Black,
                thickness = 0.5.dp
            )

            ClickableText(text = AnnotatedString(stringResource(R.string.text_rate)) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp, 25.dp, 0.dp, 0.dp),
                onClick = {
                          buttonOpenSettings.invoke()
                },
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            )
        }
    )
}