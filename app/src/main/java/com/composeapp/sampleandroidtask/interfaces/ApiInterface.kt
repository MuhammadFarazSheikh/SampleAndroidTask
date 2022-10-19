package com.composeapp.sampleandroidtask.interfaces

import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.models.WeatherMain
import com.composeapp.sampleandroidtask.networking.ApiEndPoints
import retrofit2.http.*

interface ApiInterface
{
    @GET(ApiEndPoints.SEARCH_WEATHER_BY_NAME)
    suspend fun getData(@Query("q") name:String,@Query("APPID") appId:String): Base

    @GET(ApiEndPoints.FIVE_DAYS_WEATHER)
    suspend fun getFiveDaysData(@Query("q") name:String,@Query("APPID") appId:String): WeatherMain
}