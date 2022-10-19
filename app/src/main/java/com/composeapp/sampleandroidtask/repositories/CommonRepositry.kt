package com.composeapp.sampleandroidtask.repositories

import com.composeapp.sampleandroidtask.interfaces.ApiInterface
import com.composeapp.sampleandroidtask.networking.ApiEndPoints
import com.composeapp.sampleandroidtask.repositories.BaseRepository
import javax.inject.Inject


open class CommonRepositry @Inject constructor(
    var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {

    /*API TO GET WEATHER DATA*/
    suspend fun getData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getData(name, ApiEndPoints.WEATHER_API_APP_ID)
    }

    /*API TO GET FIVE DAYS WEATHER DATA*/
    suspend fun getFiveDaysData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getFiveDaysData(name,ApiEndPoints.WEATHER_API_APP_ID)
    }
}