package com.composeapp.sampleandroidtask

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.hiltmodules.RetrofitModule.getRoomDBInstance
import com.composeapp.sampleandroidtask.models.WeatherInformation
import com.composeapp.sampleandroidtask.repositories.RoomDBRepository
import com.composeapp.sampleandroidtask.viewmodels.RoomDBViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTestCases
{
    lateinit var roomDBViewModel: RoomDBViewModel
    @Mock
    lateinit var roomDBRepository: RoomDBRepository

    @Before
    fun init()
    {
        MockitoAnnotations.openMocks(this)
        roomDBRepository.roomDBDao = getRoomDBInstance(ApplicationProvider.getApplicationContext())
        roomDBViewModel = RoomDBViewModel(roomDBRepository)
        roomDBViewModel.insertWeatherData(WeatherInformation(0,"faraz"))
        roomDBViewModel.updateWeatherData(WeatherInformation(0,"faraz"))
    }


    @Test
    fun checkIfWeatherInfoAdded()= runBlocking{
        assertThat(roomDBViewModel.getWeatherData()).isNotNull()
    }
}