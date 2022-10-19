package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*SETUP TOP APP BAR WITH ICON AND TITLE AND CLICK LISTENER FOR ICON*/
@Composable
fun topBar(onClick:()->Unit,imageVector: ImageVector,titleText:String)
{
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.DarkGray,
        title = {
                if(!titleText.isNullOrEmpty() || !titleText.isNullOrBlank())
                {
                    Text(text = titleText,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                onClick = {
                    onClick.invoke()
                },
                content = {
                    Icon(
                        imageVector,
                        contentDescription ="",
                        tint = Color.White,
                    )
                }
            )
        }
    )
}