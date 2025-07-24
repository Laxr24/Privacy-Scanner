package com.example.privacyscanner.dataTypes

import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable

data class AppInfoDataType(
    val appName: String,
    val packageName: String,
    val appIcon: Drawable,
    val isSystemApp: Boolean,
    val permissions: List<String>
)
