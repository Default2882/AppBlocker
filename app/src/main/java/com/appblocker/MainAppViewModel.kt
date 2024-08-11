package com.appblocker

import android.app.Application
import android.content.Context
import androidx.datastore.dataStore
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


val Context.appNameDataStore by dataStore(
    fileName = "appName.pb",
    serializer = AppNameSerializer
)

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

    fun getSelectedApplicationList(): List<StateFlow<AppRowState>> {
        return _installedApplicationLabelList.filter { appRowData -> appRowData.value.checked }
    }

    fun getSelectedApplicationListCount(): Int {
        return getSelectedApplicationList().size
    }
}