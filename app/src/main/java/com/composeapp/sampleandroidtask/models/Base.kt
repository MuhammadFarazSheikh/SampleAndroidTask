package com.composeapp.sampleandroidtask.models

import androidx.room.Entity
import java.util.*

@Entity
data class Base(
    val dt:String,
    val main: Main,
    val weather: ArrayList<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String,
    val name: String,
    val sys:Sys,
    var arrayList: ArrayList<Base>
)