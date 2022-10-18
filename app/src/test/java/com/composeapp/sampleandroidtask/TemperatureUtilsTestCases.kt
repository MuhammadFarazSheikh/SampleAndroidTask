package com.composeapp.sampleandroidtask

import com.composeapp.sampleandroidtask.utils.convertKelvinToCelcius
import com.composeapp.sampleandroidtask.utils.convertKelvinToFehrenheit
import com.composeapp.sampleandroidtask.utils.getWindDirection
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TemperatureUtilsTestCases
{
    @Test
    fun checkCorrectKelvinToCelcius()
    {
        assertThat(convertKelvinToCelcius(301.15)).isNotNull()
    }

    @Test
    fun checkInCorrectKelvinToCelcius()
    {
        assertThat(convertKelvinToCelcius(0.0)).isNotNull()
    }

    @Test
    fun checkCorrectKelvinToFahrenheit()
    {
        assertThat(convertKelvinToFehrenheit(301.15)).isNotNull()
    }

    @Test
    fun checkInCorrectKelvinToFajrenheit()
    {
        assertThat(convertKelvinToFehrenheit(0.0)).isNotNull()
    }

    @Test
    fun checkCorrectWindDirection()
    {
        assertThat(getWindDirection(100)).isNotEmpty()
    }

    @Test
    fun checkInCorrectWindDirection()
    {
        assertThat(getWindDirection(300)).isNotEmpty()
    }
}