package com.composeapp.sampleandroidtask.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat

/*CHECK IF LOCATION PERMISSIONS GRANTED*/
inline fun checkIfLocationPermissionGranted(context: Context): Boolean= ContextCompat
    .checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

/*CHECK IF STORAGE PERMISSIONS GRANTED*/
inline fun checkIfStoragePermissionsGranted(context: Context): Boolean{
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R) {
        if (!Environment.isExternalStorageManager()) {
            return false
        }
        else{
            return true
        }
    }
    return ContextCompat
        .checkSelfPermission(
            context,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
}