package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class TabName{
    FORECAST,
    SEARCH,
    FAVOURITES
}

/*SETUP TABS FOR HOME SCREENS OPTIONS*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupHomeScreenTabs(pagerState:PagerState,coroutineScope: CoroutineScope)
{
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        tabs = {
               setupTabs(pagerState,coroutineScope)
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.DarkGray,
        indicator = { tabPositions->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState,tabPositions
                ),
                2.dp,
                color = Color.White
            )
        }
    )
}

/*CREATE TABS INSIDE TAB ROW WIDGET*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupTabs(pagerState: PagerState,coroutineScope: CoroutineScope)
{
    TabName.values().forEachIndexed { index, tabName ->
        Tab(
            selected = pagerState.currentPage==index,
            onClick = {
                Constants.PAGER_INDEX = index
                coroutineScope.launch {
                    pagerState.animateScrollToPage(tabName.ordinal)
                }
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Gray,
            text = {
                Text(
                    text = tabName.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        )
    }
}