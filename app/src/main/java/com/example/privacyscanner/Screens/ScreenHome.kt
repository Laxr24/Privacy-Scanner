package com.example.privacyscanner.Screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.privacyscanner.Components.AppInfoCard
import com.example.privacyscanner.Components.AppTitle
import com.example.privacyscanner.Components.ApplicationStats
import com.example.privacyscanner.Components.LoadingAnimation
import com.example.privacyscanner.Components.ShareButton
import com.example.privacyscanner.utilityFunctions.shareReport
import com.example.privacyscanner.viewModel.AppListVM


@Composable
fun Home(context: Context) {


    val viewModel = viewModel<AppListVM>()
    val appInfo = viewModel.appInfo.collectAsStateWithLifecycle()
    val systemApps = viewModel.systemApps.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchAppInfo()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x00000000))
            .padding(10.dp)
    ) {

        AppTitle("Privacy Scanner")
        // Export Button
        if (appInfo.value.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            ApplicationStats(appInfo.value.size, systemApps.value)
            Spacer(Modifier.height(10.dp))
            ShareButton(onClick = {
                shareReport(
                    context,
                    "Total Installed apps: ${appInfo.value.size}\nSystem apps: ${systemApps.value}\nUser apps: ${appInfo.value.size - systemApps.value}\n\nScanner by Privacy Scanner"
                )
            }, text = "Share Data")

            Spacer(Modifier.height(4.dp))

            ShareButton(onClick = {
                viewModel.emptyInfo()
                viewModel.fetchAppInfo()
            }, text = "Scan again ⬇️")
        }

        Spacer(Modifier.height(10.dp))

        if (appInfo.value.isEmpty()) {
            LoadingAnimation()
        } else {
            LazyColumn(

            ) {
                items(appInfo.value) { app ->
                    AppInfoCard(
                        appName = app.appName,
                        score = app.permissions.size,
                        permissions = app.permissions.size.toString(),
                        isSystemApp = app.isSystemApp,
                        hasTrackers = false,
                        appIcon = app.appIcon,
                        packageName = app.packageName,
                        context = context
                    )
                    Spacer(Modifier.height(4.dp))
                }

            }
        }

    }
}


