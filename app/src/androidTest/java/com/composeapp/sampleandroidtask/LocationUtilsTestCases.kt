package com.composeapp.sampleandroidtask

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.utils.getAddress
import com.google.android.gms.maps.model.LatLng
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationUtilsTestCases
{
    @Test
    fun getAddressWithCorrectLocation()
    {
        assertThat(getAddress(33.6844,73.0479,ApplicationProvider.getApplicationContext())).isNotEmpty()
    }

    @Test
    fun getAddressWithInCorrectLocation()
    {
        assertThat(getAddress(0.0,0.0,ApplicationProvider.getApplicationContext())).isEmpty()
    }
}