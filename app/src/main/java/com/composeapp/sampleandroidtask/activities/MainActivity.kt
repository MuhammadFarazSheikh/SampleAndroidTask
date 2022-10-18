package com.composeapp.sampleandroidtask.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.composeapp.sampleandroidtask.Constants.Companion.CURRENT_CITY_NAME
import com.composeapp.sampleandroidtask.Constants.Companion.apiCallSearchWeatherLiveData
import com.composeapp.sampleandroidtask.Constants.Companion.currentWeatherUpdateLiveData
import com.composeapp.sampleandroidtask.Constants.Companion.weatherSearchUpdateLiveData
import com.composeapp.sampleandroidtask.composables.drawerContent
import com.composeapp.sampleandroidtask.composables.setupHomeScreensContent
import com.composeapp.sampleandroidtask.composables.topBar
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.models.WeatherInformation
import com.composeapp.sampleandroidtask.networking.Resource
import com.composeapp.sampleandroidtask.utils.*
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.CELCIUS
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.FAHRENHEIT
import com.composeapp.sampleandroidtask.viewmodels.CommonViewModel
import com.composeapp.sampleandroidtask.viewmodels.RoomDBViewModel
import com.composeapp.sampleandroidtask.workmanager.WeatherNotificationsWorker
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.*
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val commonViewModel: CommonViewModel by viewModels()
    private val roomDBViewModel: RoomDBViewModel by viewModels()
    private lateinit var isShowSettings:MutableState<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    mainAppStructure()
                    openSettings()
                }
            }
        )

        getUserLocation()
        callSearchWeatherApi()
    }

    /*SEARCH WEATHER DATA WITH CITY NAME ENTERED BY USER*/
    private fun callSearchWeatherApi() {
        apiCallSearchWeatherLiveData.observe(this)
        { searchString ->
            if (searchString is String) {
                commonViewModel.getData(searchString).observe(this)
                { resource ->
                    when (resource) {
                        is Resource.Success -> weatherSearchUpdateLiveData.value = resource.value
                        is Resource.Failure -> weatherSearchUpdateLiveData.value = Any()
                        is Resource.Loading -> weatherSearchUpdateLiveData.value = Any()
                    }
                }
            }
        }
    }

    /*GET USER LOCATION BY LOCATION NAME*/
    private fun getCurrentLocationWeather(name: String) {
        commonViewModel.getData(name).observe(this)
        { resourceInstance ->
            when (resourceInstance) {
                is Resource.Success -> {
                    currentWeatherUpdateLiveData.value = resourceInstance.value
                    lifecycleScope.launch {
                        if (roomDBViewModel.getWeatherData() != null) {
                            roomDBViewModel.updateWeatherData(
                                WeatherInformation(
                                    0,
                                    Gson().toJson(resourceInstance.value)
                                )
                            )
                        } else {
                            roomDBViewModel.insertWeatherData(
                                WeatherInformation(
                                    0,
                                    Gson().toJson(resourceInstance.value)
                                )
                            )
                        }
                    }
                }
                is Resource.Failure -> {
                    lifecycleScope.launch {
                        if (roomDBViewModel.getWeatherData() != null) {
                            currentWeatherUpdateLiveData.value = Gson().fromJson(
                                roomDBViewModel.getWeatherData().weatherData,
                                Base::class.java
                            )
                        }
                    }
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    /*OPEN SETTINGS TO SET TEMPERATURE TYPE*/
    @Composable
    private fun openSettings() {
        if(isShowSettings.value) {
            showSettingsDialogue(onCelciusClick = {
                lifecycleScope.launch {
                    PreferenceDataStoreUtils.storeTempratureType(CELCIUS,applicationContext)
                }
                isShowSettings.value = false
                currentWeatherUpdateLiveData.value = Any()
                getCurrentLocationWeather(CURRENT_CITY_NAME)
            },
                onFehrenheitClick = {
                    lifecycleScope.launch {
                        PreferenceDataStoreUtils.storeTempratureType(FAHRENHEIT,applicationContext)
                    }
                    isShowSettings.value = false
                    currentWeatherUpdateLiveData.value = Any()
                    getCurrentLocationWeather(CURRENT_CITY_NAME)
                })
        }
    }

    /*GET USER LOCATION AND GET LOCATION NAME FROM LATITUDE AND LONGITUDE*/
    private fun getUserLocation() {
        getUserLocation(this, { latLng ->
            CURRENT_CITY_NAME = getAddress(latLng.latitude, latLng.longitude, this)
            lifecycleScope.launch {
                PreferenceDataStoreUtils.storeLocationName(CURRENT_CITY_NAME,applicationContext)
            }
            getCurrentLocationWeather(CURRENT_CITY_NAME)
            WorkManager.getInstance(applicationContext).enqueue(PeriodicWorkRequestBuilder<WeatherNotificationsWorker>(15*60*1000,TimeUnit.MINUTES).build())
        })
    }

    /*CREATE MAIN APP STRUCTURE FOR ALL UI FUNCTIONALITY*/
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun mainAppStructure() {
        isShowSettings = remember{ mutableStateOf(false) }
        val pagerState = rememberPagerState(initialPage = 0)
        val rememberCoroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(DrawerState(DrawerValue.Closed))
        Scaffold(
            topBar = {
                topBar(
                    onClick = {
                        rememberCoroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    imageVector = Icons.Default.Menu,
                    ""
                )
            },
            scaffoldState = scaffoldState,
            content = { paddingValues ->
                setupHomeScreensContent(
                    pagerState = pagerState,
                    coroutineScope = rememberCoroutineScope
                )
            },
            drawerContent = {
                drawerContent {
                    isShowSettings.value = true
                    rememberCoroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            }
        )
    }
}