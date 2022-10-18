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
    @Test
    fun getCurrentDateWithCorrectFormat()
    {
        assertThat(getCurrentDateTimeByFormat(TIME_FORMAT_NEW)).isNotEmpty()
    }

    @Test
    fun getCurrentDateWithInCorrectFormat()
    {
        assertThat(getCurrentDateTimeByFormat("")).isEmpty()
    }

    @Test
    fun convertDateByCorrectFormat()
    {
        assertThat(convertDateByFormat(TIME_FORMAT_OLD, TIME_FORMAT_NEW,"00:00:00")).isNotEmpty()
    }

    @Test
    fun convertDateByIncorrectFormat()
    {
        assertThat(convertDateByFormat(TIME_FORMAT_OLD, TIME_FORMAT_NEW,null)).isEmpty()
    }

    @Test
    fun convertUnixTimeToFormat()
    {
        assertThat(convertUnixTimeToDateTime(TIME_FORMAT_NEW,"1666091373")).isNotNull()
    }
}