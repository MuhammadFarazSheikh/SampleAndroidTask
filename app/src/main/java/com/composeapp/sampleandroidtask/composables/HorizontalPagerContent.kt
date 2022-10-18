package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.composeapp.sampleandroidtask.Constants.Companion.currentWeatherUpdateLiveData
import com.composeapp.sampleandroidtask.Constants.Companion.favouriteCitiesListLiveData
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.utils.getFile
import com.composeapp.sampleandroidtask.utils.openLoaderDialogue
import com.composeapp.sampleandroidtask.utils.readCSVFile
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

/*SETUP HORIZONTAL PAGER*/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupHorizontalPager(pagerState: PagerState)
{
    HorizontalPager(
        count = TabName.values().size,
        content = { index->
            when(index)
            {
                0 -> when(currentWeatherUpdateLiveData.observeAsState().value)
                {
                    is Base -> setupCurrentWeatherAndMoreOptions(currentWeatherUpdateLiveData.value as Base)
                    is Any -> openLoaderDialogue()
                }
                1 -> searchWeatherContent()
                2 ->when(favouriteCitiesListLiveData.observeAsState().value)
                {
                    is ArrayList<*>-> showFavouriteCities()
                    is Any -> readCSVFile(getFile())
                }
            }
        },
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}