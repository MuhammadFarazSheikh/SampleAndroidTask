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
    fun insertWeatherData(weatherInformation: WeatherInformation)= viewModelScope.launch {
        roomDBRepository.insertWeatherData(weatherInformation)
    }

    fun updateWeatherData(weatherInformation: WeatherInformation)= viewModelScope.launch {
        roomDBRepository.updateWeatherData(weatherInformation)
    }

    suspend fun getWeatherData():WeatherInformation
    {
        return roomDBRepository.getWeatherData()
    }
}