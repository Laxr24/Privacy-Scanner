package com.example.privacyscanner

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.privacyscanner.ui.theme.PrivacyScannerTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch




data class AppInfo(
    val appName: String,
    val packageName: String,
    val permissions: List<String>
)


class MainActivity : ComponentActivity() {

    private val tag: String = "MainActivity";


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("QueryPermissionsNeeded")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrivacyScannerTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(onClick = {
                        GlobalScope.launch {
                            val installedApps: List<ApplicationInfo> =
                                packageManager.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong()))

                            for (appInfo in installedApps) {
                                val appName: String = packageManager.getApplicationLabel(appInfo).toString()
                                val icon = packageManager.getApplicationIcon(appInfo).toString()
                                val packageName: String = appInfo.packageName
                                val isSystemApp: Boolean = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                                Log.d(tag, "App Name: $appName, Package Name: $packageName, Is System App: $isSystemApp, Icon: $icon")
                            }

                        }
                    }) {
                        Text(text = "Get app info")
                    }
                }
            }
        }
    }
}


