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
import com.composeapp.sampleandroidtask.composables.showNextFiveDaysWeather
import com.composeapp.sampleandroidtask.composables.topBar
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.networking.Resource
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.NAME
import com.composeapp.sampleandroidtask.utils.openLoaderDialogue
import com.composeapp.sampleandroidtask.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityFiveDaysWeatherData : AppCompatActivity() {

    private val commonViewModel:CommonViewModel by viewModels()
    private lateinit var uiUpdateState:MutableState<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    setMainStructure()
                }
            }
        )
        initInstances()
    }

    /*CALL API FOR NEXT FIVE DAYS WEATHER DAYA FOR LOCATION*/
    private fun initInstances()
    {
        intent?.let { intentData ->
            commonViewModel.getFiveDaysData(intentData.getStringExtra(NAME)!!).observe(this)
            { resource->
                when(resource)
                {
                    is Resource.Success->{
                        var tempDate = ""
                        var tempList = arrayListOf<Base>()
                        tempDate = resource.value.list.get(0).dt_txt.split(" ")[0]

                        for (i in resource.value.list) {
                            if (!tempDate.equals(i.dt_txt.split(" ")[0])) {
                                var arrayTemp = ArrayList<Base>()

                                for (j in resource.value.list) {
                                    if (j != null && j.dt_txt.split(" ")[0].equals(i.dt_txt.split(" ")[0])) {
                                        arrayTemp.add(j)
                                    }
                                }
                                i.arrayList = ArrayList()
                                i.arrayList.addAll(arrayTemp)
                                tempList.addAll(i.arrayList)
                                tempDate = i.dt_txt.split(" ")[0]
                            }
                        }
                        uiUpdateState.value = tempList
                    }
                    is Resource.Failure -> {}
                    is Resource.Loading -> {}
                }
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
                    intent.getStringExtra(NAME)!!
                )
            },
            content = { paddingValues ->
                when(uiUpdateState.value)
                {
                    is ArrayList<*> -> showNextFiveDaysWeather(list = uiUpdateState.value as ArrayList<Base>)
                    is Any -> openLoaderDialogue()
                }
            }
        )
    }
}