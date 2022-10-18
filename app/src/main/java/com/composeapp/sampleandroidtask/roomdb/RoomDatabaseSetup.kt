package com.composeapp.sampleandroidtask.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.composeapp.sampleandroidtask.interfaces.RoomDBDao
import com.composeapp.sampleandroidtask.models.WeatherInformation

@Database(entities = arrayOf(WeatherInformation::class), version = 1)
abstract class RoomDatabaseSetup: RoomDatabase()
{
    abstract fun getRoomDBInstance(): RoomDBDao
}