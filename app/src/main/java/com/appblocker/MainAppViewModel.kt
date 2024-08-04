package com.appblocker

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainAppViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _context = application.applicationContext
    private val _pm: PackageManager = _context.packageManager
    private val _applicationInfo = _pm.getInstalledApplications(PackageManager.GET_META_DATA)

    private val _installedApplicationLabelList = _applicationInfo.mapIndexedNotNull { index, appInfo ->
        MutableStateFlow(AppRowState(
            label = _pm.getApplicationLabel(appInfo).toString(),
            icon = _pm.getApplicationIcon(appInfo),
            index = index,
            checked = false
        ))
    }

    fun getInstalledApplications(): List<StateFlow<AppRowState>> {
        return _installedApplicationLabelList
    }

    fun onCheck(index: Int) {
        _installedApplicationLabelList[index].update { currentState ->
            currentState.copy(checked = !currentState.checked) }
    }
}