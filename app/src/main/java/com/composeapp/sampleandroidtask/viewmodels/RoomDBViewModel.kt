package com.composeapp.sampleandroidtask.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeapp.sampleandroidtask.models.WeatherInformation
import com.composeapp.sampleandroidtask.repositories.RoomDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class RoomDBViewModel @Inject constructor(private val roomDBRepository: RoomDBRepository): ViewModel()
{
    /*INSERT WEATHER DATA IN LOCAL DB*/
    fun insertWeatherData(weatherInformation: WeatherInformation)= viewModelScope.launch {
        roomDBRepository.insertWeatherData(weatherInformation)
    }

    /*UPDATE WEATHER DATA IN LOCAL DB*/
    fun updateWeatherData(weatherInformation: WeatherInformation)= viewModelScope.launch {
        roomDBRepository.updateWeatherData(weatherInformation)
    }

    /*GET WEATHER DATA FROM LOCAL DB TO SYNC*/
    suspend fun getWeatherData():WeatherInformation
    {
        return roomDBRepository.getWeatherData()
    }
}