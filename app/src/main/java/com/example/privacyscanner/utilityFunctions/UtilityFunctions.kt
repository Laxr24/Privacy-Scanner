package com.example.privacyscanner.utilityFunctions

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.example.privacyscanner.dataTypes.AppInfoDataType

fun greet(){
    Log.d("tag", "Greetings Mr. Robot")
}


fun addNumber(){
    Log.d("tag", "2+3 = ${2+3}")
}


@SuppressLint("QueryPermissionsNeeded")
fun getInstalledAppsAndPermissions(packageManager: PackageManager): List<AppInfoDataType> {

    val TAG = "getInstalledAppsAndPermissions"

    val packageManager = packageManager
    val apps = mutableListOf<AppInfoDataType>()

    val flags = PackageManager.GET_META_DATA or
            (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) PackageManager.GET_PERMISSIONS else 0)


    val installedPackages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(flags.toLong()))
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
            Log.e(TAG, "Package not found: $packageName", e)
        }

        apps.add(AppInfoDataType(appName, packageName, packageIcon, isSystem, permissions))
    }
    return apps
}

