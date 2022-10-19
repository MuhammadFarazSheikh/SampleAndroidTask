package com.composeapp.sampleandroidtask

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.Constants.Companion.TIME_FORMAT_NEW
import com.composeapp.sampleandroidtask.Constants.Companion.TIME_FORMAT_OLD
import com.composeapp.sampleandroidtask.utils.convertDateByFormat
import com.composeapp.sampleandroidtask.utils.convertUnixTimeToDateTime
import com.composeapp.sampleandroidtask.utils.getCurrentDateTimeByFormat
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DateTimeUtilsTestCases
{
    /*TEST CHECK TO VERIFY CURRENT DATA WITH CORRECT FORMAT*/
    @Test
    fun getCurrentDateWithCorrectFormat()
    {
        assertThat(getCurrentDateTimeByFormat(TIME_FORMAT_NEW)).isNotEmpty()
    }

    /*TEST CASE TO VERIFY CURRENT DATE WITH INCORRECT FORMAT*/
    @Test
    fun getCurrentDateWithInCorrectFormat()
    {
        assertThat(getCurrentDateTimeByFormat("")).isEmpty()
    }

    /*TEST CASE TO VERIFY DATA CONVERSION WITH CORRECT VALUE*/
    @Test
    fun convertDateByCorrectFormat()
    {
        assertThat(convertDateByFormat(TIME_FORMAT_OLD, TIME_FORMAT_NEW,"00:00:00")).isNotEmpty()
    }

    /*CONVERT DATE WITH NULL VALUE TEST CASE*/
    @Test
    fun convertDateByIncorrectFormat()
    {
        assertThat(convertDateByFormat(TIME_FORMAT_OLD, TIME_FORMAT_NEW,null)).isEmpty()
    }

    /*TEST CASE TO CONVERT UNIX VALUE*/
    @Test
    fun convertUnixTimeToFormat()
    {
        assertThat(convertUnixTimeToDateTime(TIME_FORMAT_NEW,"1666091373")).isNotNull()
    }
}