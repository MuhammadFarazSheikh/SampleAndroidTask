package com.composeapp.sampleandroidtask.hiltmodules

import android.content.Context
import androidx.room.Room
import com.composeapp.sampleandroidtask.networking.buildApiServiceForWeatherUpdates
import com.composeapp.sampleandroidtask.roomdb.RoomDatabaseSetup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{
    @Singleton
    @Provides
    fun getInstance()= buildApiServiceForWeatherUpdates()

    @Singleton
    @Provides
    fun getRoomDBInstance(@ApplicationContext context: Context)= Room.databaseBuilder(context,
        RoomDatabaseSetup::class.java,"WeatherSearchResultsDB").build().getRoomDBInstance()
}