package com.composeapp.sampleandroidtask.utils

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.util.*

/*GET USER CURRENT LOCATION*/
fun getUserLocation(context: Context,onLocationUpdate:(LatLng)-> Unit)
{
    LocationServices
        .getFusedLocationProviderClient(context)
        .lastLocation
        .addOnSuccessListener { location->
            location?.let { result->
                onLocationUpdate.invoke(LatLng(result.latitude,result.longitude))
            }
        }
}

/*GET LOCATION NAME FROM LATITUDE AND LONGITUDE*/
fun getAddress(lat: Double, lng: Double,context: Context):String {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        if(lat==0.0 && lng==0.0)
        {
            return ""
        }
        var result =geocoder.getFromLocation(lat,lng,1)
        return result?.get(0)?.locality+","+result?.get(0)?.countryName
    }
    catch (e:Exception)
    {
        e.message
    }
    return ""
}