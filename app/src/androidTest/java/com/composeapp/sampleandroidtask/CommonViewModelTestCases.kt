package com.composeapp.sampleandroidtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.composeapp.sampleandroidtask.networking.Resource
import com.composeapp.sampleandroidtask.networking.buildApiServiceForWeatherUpdates
import com.composeapp.sampleandroidtask.repositories.CommonRepositry
import com.composeapp.sampleandroidtask.utils.getOrWaitData
import com.composeapp.sampleandroidtask.viewmodels.CommonViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CommonViewModelTestCases
{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var commonViewModel: CommonViewModel
    @Mock
    lateinit var commonRepositry: CommonRepositry

    @Before
    fun init()
    {
        MockitoAnnotations.openMocks(this)
        commonRepositry.apiInterfaceWeatherUpdates = buildApiServiceForWeatherUpdates()
        commonViewModel = CommonViewModel(commonRepositry)
    }

    @Test
    fun testApICallsForResponse()
    {
        commonViewModel.getData("Islamabad").getOrWaitData().let {
            when(it)
            {
                is Resource.Success->{
                    assertThat(it.value).isNotNull()
                }
                is Resource.Failure->{
                }
                is Resource.Loading->{

                }
            }
        }
    }

    @Test
    fun testApICallsForError()
    {
        commonViewModel.getData("").getOrWaitData().let {
            when(it)
            {
                is Resource.Success->{
                }
                is Resource.Failure->{
                    assertThat(it.errorBody).isNotNull()
                }
                is Resource.Loading->{

                }
            }
        }
    }

    @Test
    fun testApICallsForResponseForFiveDays()
    {
        commonViewModel.getFiveDaysData("Islamabad").getOrWaitData().let {
            when(it)
            {
                is Resource.Success->{
                    assertThat(it.value).isNotNull()
                }
                is Resource.Failure->{
                }
                is Resource.Loading->{

                }
            }
        }
    }

    @Test
    fun testApICallsForErrorForFiveDays()
    {
        commonViewModel.getData("").getOrWaitData().let {
            when(it)
            {
                is Resource.Success->{
                }
                is Resource.Failure->{
                    assertThat(it.errorBody).isNotNull()
                }
                is Resource.Loading->{

                }
            }
        }
    }
}