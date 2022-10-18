package com.composeapp.sampleandroidtask.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.composeapp.sampleandroidtask.models.WeatherInformation

@Dao
interface RoomDBDao
{
    @Insert
    suspend fun insertWeatherData(weatherInformation: WeatherInformation)

    @Update
    suspend fun updateWeatherData(weatherInformation: WeatherInformation)

    @Query("SELECT * FROM WEATHERINFORMATION")
    suspend fun getWeatherData():WeatherInformation
}