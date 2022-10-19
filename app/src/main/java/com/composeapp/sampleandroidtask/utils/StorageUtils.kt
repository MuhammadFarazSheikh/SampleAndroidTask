package com.composeapp.sampleandroidtask.utils

import android.os.Environment
import android.widget.Toast
import com.composeapp.sampleandroidtask.AppClass.Companion.instance
import com.composeapp.sampleandroidtask.Constants.Companion.CSV_FILE_NAME
import com.composeapp.sampleandroidtask.Constants.Companion.favouriteCitiesList
import com.composeapp.sampleandroidtask.Constants.Companion.favouriteCitiesListLiveData
import com.composeapp.sampleandroidtask.R
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

/*LOCATION OF FILE IN DOWNLOADS PATH*/
inline fun getFileLocation():String= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + CSV_FILE_NAME

/*CREATE FILE FROM PATH IF DOESNOT EXIST*/
fun getFile():File
{
    var file = File(getFileLocation())
    if(!file.exists())
    {
        file.createNewFile()
    }
    return file
}

/*WRITE USER ADDED LOCATION NAME TO CSV FILE*/
fun writeToCSV() {


    try {
        var writer = CSVWriter(FileWriter(getFile()));
        favouriteCitiesList.forEach {
            writer.writeNext(arrayOf(it));
        }
        // data is adding to csv

        writer.close();
        Toast.makeText(instance, instance.getString(R.string.text_wriiten),Toast.LENGTH_LONG).show()
        favouriteCitiesListLiveData.value = Any()
        //readCSVFile(file  = file)
    } catch (e: IOException) {
        e.printStackTrace();
    }
}

/*READ USER ADDED LOCATION NAMES FROM CVS FILE*/
fun readCSVFile(file: File)
{
    favouriteCitiesList.clear()
    var reader = CSVReader(FileReader(file))
    reader.readAll().forEach {
        favouriteCitiesList.add(it.get(0))
    }
    reader.close()
    favouriteCitiesListLiveData.value = favouriteCitiesList
}




