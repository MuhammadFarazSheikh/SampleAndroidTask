package com.composeapp.sampleandroidtask

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.utils.getAddress
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationUtilsTestCases
{
    /*GET CURRENT LOCATION NAME TEST CASE WITH CORRECT LOCATION*/
    @Test
    fun getAddressWithCorrectLocation()
    {
        assertThat(getAddress(33.6844,73.0479,ApplicationProvider.getApplicationContext())).isNotEmpty()
    }

    /*GET CURRENT LOCATION NAME TEST CASE WITH INCORRECT LOCATION*/
    @Test
    fun getAddressWithInCorrectLocation()
    {
        assertThat(getAddress(0.0,0.0,ApplicationProvider.getApplicationContext())).isEmpty()
    }
}