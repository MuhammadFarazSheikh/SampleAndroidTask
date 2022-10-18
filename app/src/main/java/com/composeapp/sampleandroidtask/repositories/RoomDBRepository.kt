package com.composeapp.sampleandroidtask.repositories

import com.composeapp.sampleandroidtask.interfaces.RoomDBDao
import com.composeapp.sampleandroidtask.models.WeatherInformation
import javax.inject.Inject

open class RoomDBRepository @Inject constructor(var roomDBDao: RoomDBDao)
{
    suspend fun insertWeatherData(weatherInformation: WeatherInformation)
    {
        roomDBDao.insertWeatherData(weatherInformation)
    }

    suspend fun updateWeatherData(weatherInformation: WeatherInformation)
    {
        roomDBDao.updateWeatherData(weatherInformation)
    }

    suspend fun getWeatherData():WeatherInformation
    {
        return roomDBDao.getWeatherData()
    }
}