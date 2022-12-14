package com.composeapp.sampleandroidtask

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.utils.PreferenceDataStoreUtils
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferenceDataStoreTestCases
{
    /*INIT VALUES IN PREFERENCE STORAGE BEFORE TEST CASES EXECUTE*/
    @Before
    fun init()
    {
        runBlocking {
            PreferenceDataStoreUtils.storeTempratureType("faraz",ApplicationProvider.getApplicationContext())
            PreferenceDataStoreUtils.storeLocationName("Islamabad",ApplicationProvider.getApplicationContext())
        }
    }

    /*TEST CASE TO CHECK IF TEMPERATURE STORED*/
    @Test
    fun checkTemperatureValue()
    {
        assertThat(PreferenceDataStoreUtils.getTemperatureType(ApplicationProvider.getApplicationContext())).isNotNull()
    }

    /*TEST CASE TO CHECK CORRECT LOCATION VALUE SAVED*/
    @Test
    fun checLocationValue()
    {
        assertThat(PreferenceDataStoreUtils.getLocationName(ApplicationProvider.getApplicationContext())).isNotNull()
    }
}