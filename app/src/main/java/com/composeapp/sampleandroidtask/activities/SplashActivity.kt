package com.composeapp.sampleandroidtask.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.composeapp.sampleandroidtask.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.sampleandroidtask.BuildConfig
import com.composeapp.sampleandroidtask.utils.checkIfLocationPermissionGranted
import com.composeapp.sampleandroidtask.utils.checkIfStoragePermissionsGranted
import com.composeapp.sampleandroidtask.utils.openAlertForMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var mutableState: MutableState<Boolean>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    mainStructure()
                    checkAndGrantPermission()
                }
            }
        )
        initInstances()
        startTimer()
    }

    /*CREATE INSTANCES*/
    private fun initInstances() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (checkIfLocationPermissionGranted(this) && checkIfStoragePermissionsGranted(this)) {
                    openMainScreen()
                } else {
                    mutableState.value = true
                }
            }
    }

    /*START TIMER TO CHECK FOR LOCATION PERMISSION GRANTED OR NOT*/
    /*THEN PROCEED FURTHER*/
    private fun startTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                if (!checkIfLocationPermissionGranted(applicationContext) || !checkIfStoragePermissionsGranted(
                        applicationContext
                    )
                ) {
                    mutableState.value = true
                } else {
                    openMainScreen()
                }
            }
        }.start()
    }

    /*CHECK AND GRANT LOCATION PERMISSION IF NOT GRANTED*/
    @Composable
    private fun checkAndGrantPermission() {
        if (mutableState.value) {

            openAlertForMessage(
                title = stringResource(R.string.text_allow_permissions),
                message = stringResource(R.string.text_description),
                confirmButtonListener = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
                        openSettings()
                    } else {
                        openAppSettings()
                    }
                },
                dimissButtonListener = {
                    finish()
                }
            )
        }
    }

    private fun openAppSettings() {
        mutableState.value = false
        activityResultLauncher.launch(
            Intent(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                )
            )
        )
    }

    private fun openSettings() {
        mutableState.value = false
        var intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.addCategory("android.intent.category.DEFAULT")
        intent.setData(Uri.fromParts("package", packageName, null))
        activityResultLauncher.launch(
            intent
        )
    }

    private fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }

    @Composable
    fun mainStructure() {
        mutableState = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),
            content = {

                Text(
                    stringResource(R.string.text_label),
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(R.drawable.weather_data_image),
                    contentDescription = "",
                    modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
                )
            },
            verticalArrangement = Arrangement.Center
        )
    }
}