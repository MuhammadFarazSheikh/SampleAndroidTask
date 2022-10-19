package com.composeapp.sampleandroidtask.repositories

import com.composeapp.sampleandroidtask.interfaces.RoomDBDao
import com.composeapp.sampleandroidtask.models.WeatherInformation
import javax.inject.Inject

open class RoomDBRepository @Inject constructor(var roomDBDao: RoomDBDao)
{
    /*INSERT WEATHER DATA IN LOCAL DB FOR DATA SYNC*/
    suspend fun insertWeatherData(weatherInformation: WeatherInformation)
    {
        roomDBDao.insertWeatherData(weatherInformation)
    }

    /*UPDATE WEATHER DATA*/
    suspend fun updateWeatherData(weatherInformation: WeatherInformation)
    {
        roomDBDao.updateWeatherData(weatherInformation)
    }

    /*GET STORED WEATHER DATA*/
    suspend fun getWeatherData():WeatherInformation
    {
        return roomDBDao.getWeatherData()
    }
}