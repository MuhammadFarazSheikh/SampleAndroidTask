package com.composeapp.sampleandroidtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.composeapp.sampleandroidtask.repositories.CommonRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
open class CommonViewModel @Inject constructor(
    var commonRepositry: CommonRepositry
    ): ViewModel() {

    fun getData(name:String)= liveData{
        emit(commonRepositry.getData(name))
    }
    fun getFiveDaysData(name:String)= liveData{
        emit(commonRepositry.getFiveDaysData(name))
    }
}