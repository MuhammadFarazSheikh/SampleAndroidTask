package com.composeapp.sampleandroidtask.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.composeapp.sampleandroidtask.Constants.Companion.USER_PREFERENCES_NAME
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.LOCATION_NAME
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.TEMPERATURE_CONVERSION_TYPE
import kotlinx.coroutines.flow.map

object PreferenceDataStoreUtils
{
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(USER_PREFERENCES_NAME)
    private val temperatureType = stringPreferencesKey(TEMPERATURE_CONVERSION_TYPE)
    private val locationName = stringPreferencesKey(LOCATION_NAME)

    /*STORE USER SELECTED TEMPERATURE TYPE*/
    suspend fun storeTempratureType(type:String,context:Context)
    {
        context.dataStore.edit { preferencesDataStore->
            preferencesDataStore[temperatureType] = type
        }
    }

    /*READ USER SELECTED TEMPERATURE TYEP*/
    fun getTemperatureType(context: Context)=context.dataStore.data.map { data->
        data[temperatureType]?:""
    }

    /*STORE USER SELECTED TEMPERATURE TYPE*/
    suspend fun storeLocationName(name:String,context:Context)
    {
        context.dataStore.edit { preferencesDataStore->
            preferencesDataStore[locationName] = name
        }
    }

    /*READ USER SELECTED TEMPERATURE TYEP*/
    fun getLocationName(context: Context)=context.dataStore.data.map { data->
        data[locationName]?:""
    }
}