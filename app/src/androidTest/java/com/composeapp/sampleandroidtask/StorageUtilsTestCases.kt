package com.composeapp.sampleandroidtask

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.utils.getFile
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StorageUtilsTestCases
{
    /*TEST CASE TO CHECK FILE INSTANCE NOT NULL*/
    @Test
    fun checkCorrectFileInstance()
    {
        assertThat(getFile()).isNotNull()
    }

    /*TEST CASE TO CHECK FILE PATH NOT NULL*/
    @Test
    fun checkCorrectFilePath()
    {
        assertThat(getFile().absoluteFile).isNotNull()
    }
}