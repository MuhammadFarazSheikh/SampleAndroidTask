package com.composeapp.sampleandroidtask.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.composeapp.sampleandroidtask.Constants.Companion.TIME_FORMAT_NEW
import com.composeapp.sampleandroidtask.R
import com.composeapp.sampleandroidtask.activities.MainActivity
import com.composeapp.sampleandroidtask.networking.ApiEndPoints
import com.composeapp.sampleandroidtask.networking.buildApiServiceForWeatherUpdates
import com.composeapp.sampleandroidtask.utils.PreferenceDataStoreUtils
import com.composeapp.sampleandroidtask.utils.convertKelvinToCelcius
import com.composeapp.sampleandroidtask.utils.getCurrentDateTimeByFormat
import kotlinx.coroutines.flow.collectLatest

class WeatherNotificationsWorker(var context: Context,var workerParameters: WorkerParameters): CoroutineWorker(context,workerParameters)
{
    override suspend fun doWork(): Result {
        if(getCurrentDateTimeByFormat(TIME_FORMAT_NEW).uppercase().equals("06:00 AM")) {
            PreferenceDataStoreUtils.getLocationName(context).collectLatest {
                var result =
                    buildApiServiceForWeatherUpdates().getData(it, ApiEndPoints.WEATHER_API_APP_ID)
                showWeatherNotification(result.main.temp)
            }
        }
        return Result.success()
    }

    private fun showWeatherNotification(temp:String)
    {
        var notificationCompact = NotificationCompat.Builder(context)
        notificationCompact.setSmallIcon(R.drawable.ic_launcher_foreground)
        notificationCompact.setContentText("Todays weather : "+ convertKelvinToCelcius(temp.toDouble()).toInt().toString() + "\u2103")
        notificationCompact.setContentTitle("Weather Update")
        notificationCompact.setAutoCancel(true)
        notificationCompact.setOngoing(false)
        notificationCompact.setContentIntent(PendingIntent.getActivity(context,1,
            Intent(context,MainActivity::class.java),PendingIntent.FLAG_UPDATE_CURRENT))

        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var notificationChannel = NotificationChannel("channel","channel", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(1000,notificationCompact.build())
    }
}