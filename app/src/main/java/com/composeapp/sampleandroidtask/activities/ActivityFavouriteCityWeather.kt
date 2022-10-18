package com.composeapp.sampleandroidtask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import com.composeapp.sampleandroidtask.composables.setupCurrentWeatherAndMoreOptions
import com.composeapp.sampleandroidtask.composables.topBar
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.networking.Resource
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.NAME
import com.composeapp.sampleandroidtask.utils.openLoaderDialogue
import com.composeapp.sampleandroidtask.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityFavouriteCityWeather : AppCompatActivity() {

    private lateinit var uiUpdateState:MutableState<Any>
    private val commonViewModel:CommonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    setMainStructure()
                }
            }
        )
        getWeatherData()
    }

    /*CALL API TO GET WEATHER DATA*/
    private fun getWeatherData()
    {
        commonViewModel.getData(intent.getStringExtra(NAME)!!).observe(this)
        { resource->
            when(resource)
            {
                is Resource.Success -> uiUpdateState.value = resource.value
                is Resource.Failure-> {}
                is Resource.Loading -> {}
            }
        }
    }

    @Composable
    fun setMainStructure()
    {
        uiUpdateState = remember{ mutableStateOf(Any()) }
        Scaffold(
            topBar = {
                topBar(
                    onClick = {
                        finish()
                    },
                    imageVector = Icons.Default.ArrowBack,
                    titleText = intent.getStringExtra(NAME)!!
                )
            },
            content = { paddingValues ->
                when(uiUpdateState.value)
                {
                    is Base -> setupCurrentWeatherAndMoreOptions(base = uiUpdateState.value as Base)
                    is Any -> openLoaderDialogue()
                }
            }
        )
    }
}