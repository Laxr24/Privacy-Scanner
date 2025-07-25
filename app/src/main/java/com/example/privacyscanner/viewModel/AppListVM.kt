package com.example.privacyscanner.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.privacyscanner.dataTypes.AppInfoDataType
import com.example.privacyscanner.utilityFunctions.getInstalledAppsAndPermissions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class AppListVM(application: Application) : AndroidViewModel(application) {
    private var context: Context = application.applicationContext
    private val _appInfo: MutableStateFlow<List<AppInfoDataType>> = MutableStateFlow(emptyList())
    val appInfo: StateFlow<List<AppInfoDataType>> = _appInfo.asStateFlow()

    fun updateAppInfo() {
        viewModelScope.launch {
            getInstalledAppsAndPermissions(context.packageManager,
                _appInfo.value as MutableList<AppInfoDataType>
            )
        }
    }

    init{
        updateAppInfo()
    }

}