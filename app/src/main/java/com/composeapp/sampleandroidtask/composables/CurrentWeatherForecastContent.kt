package com.composeapp.sampleandroidtask.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.R
import coil.compose.rememberAsyncImagePainter
import com.composeapp.sampleandroidtask.AppClass.Companion.instance
import com.composeapp.sampleandroidtask.Constants
import com.composeapp.sampleandroidtask.models.Base
import com.composeapp.sampleandroidtask.utils.*
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.CELCIUS
import com.composeapp.sampleandroidtask.utils.KeyUtils.Companion.FAHRENHEIT

/*SHOW WEATHER CONTENT*/
@Composable
fun showWeatherForecast(base: Base) {
    if(!base.name.isNullOrBlank() || !base.name.isNullOrEmpty()) {
        Text(
            base.name + (", " + base.sys.country ?: ""),
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 5.dp, 0.dp, 0.dp)
        )
    }

    if(!base.main.temp.isNullOrBlank() || !base.main.temp.isNullOrEmpty()) {
        var temp = PreferenceDataStoreUtils.getTemperatureType(instance).collectAsState(initial = "")
        var tempValue = ""
        if(temp.value.isNullOrEmpty() || temp.value.isNullOrBlank())
        {
            tempValue = convertKelvinToCelcius(base.main.temp.toDouble()).toInt().toString() + "\u2103"
        }
        else if(temp.value.equals(CELCIUS))
        {
            tempValue = convertKelvinToCelcius(base.main.temp.toDouble()).toInt().toString() + "\u2103"
        }
        else if(temp.value.equals(FAHRENHEIT))
        {
            tempValue = convertKelvinToFehrenheit(base.main.temp.toDouble()).toInt().toString() + "\u2109"
        }

        Text(
            tempValue,
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
        )
    }

    if(!base.weather.isEmpty() && base.weather.size>0) {

        Text(
            base.weather.get(0).description,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }

    Image(
        painter = rememberAsyncImagePainter(
            stringResource(R.string.base_url_image)
                    + (base.weather.get(0).icon
                    + stringResource(
                R.string.image_extension
            ))
        ),
        contentDescription = "",
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .width(70.dp)
            .height(70.dp),
    )

    Text(
        stringResource(R.string.text_wind) + getWindDirection(base.wind.deg.toDouble().toInt()) +
                "," + base.wind.speed + " " + stringResource(R.string.text_km_h),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
    )

    Text(
        "Max:" + convertKelvinToCelcius(base.main.temp_max.toDouble()).toInt()
            .toString() + "\u2103" +
                " / Min:" + convertKelvinToCelcius(base.main.temp_min.toDouble()).toInt()
            .toString() + "\u2103",
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
    )

    Text(
        stringResource(R.string.text_pressure) + " " + base.main.pressure.toString() + " " +
                stringResource(R.string.text_hpa) + " / "
                + stringResource(R.string.text_humidity) + " " + base.main.humidity.toString() + " " +
                stringResource(R.string.text_hpa),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
    )

    if(!base.sys.sunrise.isNullOrBlank() || !base.sys.sunrise.isNullOrEmpty()) {
        Text(
            stringResource(R.string.text_sunrise) + " " + convertUnixTimeToDateTime(
                base.sys.sunrise,
                Constants.DATE_TIME_FORMAT
            ),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
    }

    if(!base.sys.sunset.isNullOrEmpty() || !base.sys.sunset.isNullOrBlank()) {
        Text(
            stringResource(R.string.text_sunset) + " " + convertUnixTimeToDateTime(
                base.sys.sunset,
                Constants.DATE_TIME_FORMAT
            ),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
    }

    if(!base.dt_txt.isNullOrEmpty() || !base.dt_txt.isNullOrBlank()) {
        Text(
            text = stringResource(R.string.text_date)+" "+base.dt_txt.split(" ").get(0) + "  "
                    + stringResource(R.string.text_time)+" "+convertDateByFormat(
                Constants.TIME_FORMAT_OLD,
                Constants.TIME_FORMAT_NEW,
                base.dt_txt.split(" ").get(1),
            ),
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
    }
}