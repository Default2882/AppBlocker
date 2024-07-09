package com.appblocker

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel

class MainAppViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val pm: PackageManager = context.packageManager
    private val applicationInfo = pm.getInstalledApplications(PackageManager.GET_META_DATA)

    private var installedApplicationLabelList =
        applicationInfo.mapIndexedNotNull { index, appInfo ->
            AppRowData(
                label = pm.getApplicationLabel(appInfo).toString(),
                icon = pm.getApplicationIcon(appInfo)
            )
        }.sortedBy { it.label }

    fun getInstalledApplications(): List<AppRowData> {
        return installedApplicationLabelList
    }
}