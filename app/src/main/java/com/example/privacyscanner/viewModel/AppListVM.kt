package com.example.privacyscanner.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.privacyscanner.dataTypes.AppInfoDataType
import com.example.privacyscanner.utilityFunctions.getInstalledAppsAndPermissions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class AppListVM(application: Application) : AndroidViewModel(application) {

    private var context: Context = application.applicationContext
    private val _appInfo = MutableStateFlow<List<AppInfoDataType>>(emptyList())
    private val _systemApps = MutableStateFlow<Int>(0)

    val systemApps = _systemApps.asStateFlow()
    val appInfo: StateFlow<List<AppInfoDataType>> = _appInfo.asStateFlow()


    fun fetchAppInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val packageManager = context.packageManager
            val apps = async { getInstalledAppsAndPermissions(packageManager) }
            _appInfo.value = apps.await()
            _systemApps.value = _appInfo.value.count { it.isSystemApp }
            Log.d("getInstalledAppsAndPermissions", "App Info Updated ${_appInfo.value.size}")
        }
    }


    fun emptyInfo(){
        _appInfo.value = emptyList<AppInfoDataType>()
    }


}