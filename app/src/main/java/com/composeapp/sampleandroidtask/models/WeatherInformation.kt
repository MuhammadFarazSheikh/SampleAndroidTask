package com.composeapp.sampleandroidtask.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherInformation(
    @PrimaryKey(autoGenerate = false)
    var id:Int=0,
    var weatherData:String

)