package com.example.privacyscanner.utilityFunctions

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.privacyscanner.dataTypes.AppInfoDataType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


suspend fun getInstalledAppsAndPermissions(packageManager: PackageManager): List<AppInfoDataType> {

    val tag = "getInstalledAppsAndPermissions"

    val packageManager = packageManager
    val apps = mutableListOf<AppInfoDataType>()

    coroutineScope {
        launch(Dispatchers.Default) {

            val flags = PackageManager.GET_META_DATA or
                    (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) PackageManager.GET_PERMISSIONS else 0)


            val installedPackages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getInstalledApplications(
                    PackageManager.ApplicationInfoFlags.of(
                        PackageManager.GET_META_DATA.toLong()
                    )
                )
            } else {
                @Suppress("DEPRECATION")
                packageManager.getInstalledApplications(flags)
            }


            for (appInfo in installedPackages) {
                val appName = appInfo.loadLabel(packageManager).toString()
                val packageName = appInfo.packageName
                val permissions = mutableListOf<String>()
                val packageIcon = packageManager.getApplicationIcon(packageName)
                val isSystem = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0

                try {
                    // Get package info to retrieve permissions
                    val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        packageManager.getPackageInfo(
                            packageName,
                            PackageManager.PackageInfoFlags.of(PackageManager.GET_PERMISSIONS.toLong())
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
                    }

                    packageInfo.requestedPermissions?.let {
                        for (permission in it) {
                            permissions.add(permission)
                        }
                    }
                } catch (e: PackageManager.NameNotFoundException) {
                    Log.e(tag, "Package not found: $packageName", e)
                }
                apps.add(AppInfoDataType(appName, packageName, packageIcon, isSystem, permissions))
            }
        }
    }
    return apps
}

fun shareReport(context: Context, textToShare: String, chooserTitle: String? = "Share via") {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, textToShare)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, chooserTitle)

    if (sendIntent.resolveActivity(context.packageManager) != null) {
        ContextCompat.startActivity(context, shareIntent, null)
    } else {
        Toast.makeText(context, "No app available to share this content", Toast.LENGTH_SHORT).show()
    }
}


fun appInfoPage(context: Context, packageName: String) {
    try {

        // Initial check for the current package information
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(packageName, 0)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(context, "App not found: $packageName", Toast.LENGTH_SHORT).show()
            return
        }

        // Explicit intent to open app settings page

        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            ContextCompat.startActivity(context, intent, null)
        } else {
            Toast.makeText(context, "Could not open app settings", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Error opening app settings: ${e.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
        e.printStackTrace()
    }
}