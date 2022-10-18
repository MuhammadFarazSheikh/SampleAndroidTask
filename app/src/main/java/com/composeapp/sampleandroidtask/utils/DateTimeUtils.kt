package com.composeapp.sampleandroidtask.utils

import java.text.SimpleDateFormat
import java.util.*

/*GET CURRENT DATE OR TIME BY FORMAT*/
fun getCurrentDateTimeByFormat(
    dateFormat: String): String {
    dateFormat?.let {
        return SimpleDateFormat(it).format(Calendar.getInstance().time).toString()
    }
    return ""
}

/*CONVERT DATE BY FORMAT*/
fun convertDateByFormat(
    oldDateFormat: String
    ,newDateFormat:String
    ,dateValue:String?):String{
    dateValue?.let {
        return SimpleDateFormat(newDateFormat).format(SimpleDateFormat(oldDateFormat).parse(dateValue))
    }
    return ""
}

/*CONVERT UNIX TIME IN PROVIDED FORMAT*/
fun convertUnixTimeToDateTime(unixValue:String,format:String):String
{
    var value = ""
    unixValue?.let {
        value =SimpleDateFormat(format).format(Date(it.toLong()*1000))
    }
    return value
}