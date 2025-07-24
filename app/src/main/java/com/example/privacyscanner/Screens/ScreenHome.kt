package com.example.privacyscanner.Screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.privacyscanner.Components.AppInfoCard
import com.example.privacyscanner.Components.AppTitle
import com.example.privacyscanner.Components.ExportDataButton
import com.example.privacyscanner.viewModel.AppListVM


@Composable
fun Home(context: Context) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val viewModel = viewModel<AppListVM>()

        val appInfo = viewModel.appInfo.collectAsStateWithLifecycle()


        // Start of UI Components

        AppTitle()

        Spacer(Modifier.height(10.dp))
        ExportDataButton(onClick = {
            Log.d("tag", "Export Data to to CSV from Database")
        }, text = "Export Data")
        Spacer(Modifier.height(10.dp))

        if (appInfo.value.isEmpty()) {
            CircularProgressIndicator()
        } else {
            // All ApplicationInfo
            for (app in appInfo.value) {
                AppInfoCard(
                    appName = app.appName,
                    score = when (app.permissions.size) {
                        0 -> {
                            100
                        }

                        in 1..5 -> {
                            95
                        }

                        in 6..10 -> {
                            75
                        }

                        in 11..15 -> {
                            25
                        }

                        in 15..50 -> {
                            10
                        }

                        else -> {
                            0
                        }
                    },
                    permissions = app.permissions.size.toString(),
                    isSystemApp = app.isSystemApp,
                    hasTrackers = false,
                    appIcon = app.appIcon
                )
                Log.d("tag", "${app.appIcon}")
                Spacer(Modifier.height(4.dp))
            }
        }

    }
}


