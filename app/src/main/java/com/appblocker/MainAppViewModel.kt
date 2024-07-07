package com.appblocker

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

class MainAppViewModel @Inject constructor(
    context: Context
): ViewModel() {
//    var installedApplicationLabelList by mutableStateOf(listOf<AppRowData>())

    val pm: PackageManager = context.packageManager
    val applicationInfo = pm.getInstalledApplications(PackageManager.GET_META_DATA)

    var installedApplicationLabelList = applicationInfo.mapIndexedNotNull { index, appInfo ->
        AppRowData(
            label = pm.getApplicationLabel(appInfo).toString(),
            icon = pm.getApplicationIcon(appInfo)
        )
    }.sortedBy { it.label }

    fun getInstalledApplications(): List<AppRowData> {
        return installedApplicationLabelList
    }
}