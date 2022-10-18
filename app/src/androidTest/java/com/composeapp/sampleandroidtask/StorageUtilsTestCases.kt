package com.composeapp.sampleandroidtask

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.composeapp.sampleandroidtask.utils.getFile
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StorageUtilsTestCases
{
    @Test
    fun checkCorrectFileInstance()
    {
        assertThat(getFile()).isNotNull()
    }

    @Test
    fun checkCorrectFilePath()
    {
        assertThat(getFile().absoluteFile).isNotNull()
    }
}