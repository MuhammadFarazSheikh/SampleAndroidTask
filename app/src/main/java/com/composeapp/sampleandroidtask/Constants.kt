package com.composeapp.sampleandroidtask

import androidx.lifecycle.MutableLiveData

class Constants
{
    companion object {
        var CURRENT_CITY_NAME = ""
        var SEARCHED_CITY_NAME = ""
        var FAVOURITE_CITY_NAME = ""
        var PAGER_INDEX = 0
        const val USER_PREFERENCES_NAME = "LOCAL_STORAGE_PREFERENCE"
        val currentWeatherUpdateLiveData = MutableLiveData<Any>(Any())
        val weatherSearchUpdateLiveData = MutableLiveData<Any>(Any())
        val apiCallSearchWeatherLiveData = MutableLiveData<String>()
        val favouriteCitiesListLiveData = MutableLiveData<Any>(Any())
        val favouriteCitiesList = ArrayList<String>()
        const val DATE_FORMAT_NEW = "dd-MM-yyyy"
        const val CSV_FILE_NAME = "/FavouriteCitiesList.csv"
        const val TIME_FORMAT_NEW = "hh:mm a"
        const val TIME_FORMAT_OLD = "hh:mm:ss"
        const val DATE_TIME_FORMAT = "dd-MM-yyyy / hh:mm a"
    }
}