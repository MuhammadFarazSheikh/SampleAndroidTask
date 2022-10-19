package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

/*SETUP HOME SCREEN CONTENT WITH TABS AND PAGER VIEW*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupHomeScreensContent(pagerState: PagerState,coroutineScope: CoroutineScope)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        content = {
            setupHomeScreenTabs(pagerState = pagerState, coroutineScope = coroutineScope )
            setupHorizontalPager(pagerState = pagerState)
        }
    )
}