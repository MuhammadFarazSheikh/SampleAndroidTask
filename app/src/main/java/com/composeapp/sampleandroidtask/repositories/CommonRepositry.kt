package com.composeapp.sampleandroidtask.repositories

import com.composeapp.sampleandroidtask.interfaces.ApiInterface
import com.composeapp.sampleandroidtask.networking.ApiEndPoints
import com.composeapp.sampleandroidtask.repositories.BaseRepository
import javax.inject.Inject


open class CommonRepositry @Inject constructor(
    var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {

    suspend fun getData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getData(name, ApiEndPoints.WEATHER_API_APP_ID)
    }

    suspend fun getFiveDaysData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getFiveDaysData(name,ApiEndPoints.WEATHER_API_APP_ID)
    }
}