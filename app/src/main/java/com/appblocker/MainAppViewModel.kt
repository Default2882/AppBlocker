package com.appblocker

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainAppViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _context = application.applicationContext
    private val _pm: PackageManager = _context.packageManager
    private val _applicationInfo = _pm.getInstalledApplications(PackageManager.GET_META_DATA)

    private var _installedApplicationLabelList = _applicationInfo.mapIndexedNotNull { index, appInfo ->
        AppRowState(
            label = _pm.getApplicationLabel(appInfo).toString(),
            icon = _pm.getApplicationIcon(appInfo)
        )
    }.sortedBy { it.label }

    val installedApplicationStateList = _installedApplicationLabelList.map { appRowState ->
        MutableStateFlow(appRowState).asStateFlow()
    }
    fun getInstalledApplications(): List<StateFlow<AppRowState>> {
        return installedApplicationStateList
    }
}