package com.composeapp.sampleandroidtask.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch

fun <T> LiveData<T>.getOrWaitData():T
{
    var data:T?=null
    val countDownLatch = CountDownLatch(1)

    var observer = object:Observer<T>
    {
        override fun onChanged(t: T) {
            data = t
            this@getOrWaitData.removeObserver(this)
            countDownLatch.countDown()
        }
    }

    this.observeForever(observer)
    countDownLatch.await()

    return data as T
}