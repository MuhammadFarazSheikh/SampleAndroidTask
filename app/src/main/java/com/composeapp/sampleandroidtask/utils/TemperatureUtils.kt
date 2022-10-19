package com.composeapp.sampleandroidtask.utils

/*CONVERT FROM KELVIN TO CELCIUS*/
inline fun convertKelvinToCelcius(kelvin: Double): Double {
    return kelvin - 273.15
}

/*CONVERT FROM KELVIN TO FEHRENHEIT*/
inline fun convertKelvinToFehrenheit(kelvin: Double): Double {
    return 1.8*(kelvin-273) + 32
}

/*GET WIND DIRECTION*/
inline fun getWindDirection(degrees: Int): String? {
    var windDirection = ""
    windDirection = if (degrees >= 0 && degrees < 90) {
        "N"
    } else if (degrees >= 90 && degrees < 180) {
        "E"
    } else if (degrees >= 180 && degrees < 270) {
        "S"
    } else {
        "W"
    }
    return windDirection
}