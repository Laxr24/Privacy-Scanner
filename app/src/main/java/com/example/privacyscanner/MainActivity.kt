package com.example.privacyscanner

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.privacyscanner.Screens.Home
import com.example.privacyscanner.ui.theme.PrivacyScannerTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.toString


data class AppInfo(
    val appName: String,
    val packageName: String,
    val permissions: List<String>,
    val icon: String?
)



private val tag: String = "MainActivity";


class MainActivity : ComponentActivity() {



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("QueryPermissionsNeeded")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xffffffff))
                ){
                    Home()
                    ScoreGrid()

                    Button(onClick = {
                        getAppInfo(applicationContext)
                    }) {
                        Text("Click me")
                    }

                }

        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun getAppInfo(context: Context){
    GlobalScope.launch {
        val packageManager: PackageManager = context.packageManager
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
}
@Composable
fun ScoreGrid() {
    val itemsList = listOf(
        "Privacy Score: 70", "Total Apps: 25", "System apps: 12", "Installed: 13"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Defines 2 columns
        modifier = Modifier
            .padding(6.dp),
    ) {
        items(itemsList) { itemText ->
            // Box is used here to help with centering the Text within the grid cell.
            // You could also apply padding or other modifiers directly to Text if needed.
            Box(
                contentAlignment = Alignment.Center, // Centers the content (Text) within the Box
                modifier = Modifier.padding(5.dp) // Optional padding around each item
                .background(color = Color(0xFF9DFF89))

            ) {
                Text(text = itemText, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
            }
        }
    }
}



