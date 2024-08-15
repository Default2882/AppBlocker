package com.appblocker.data

import android.content.Context
import android.content.pm.PackageManager
import com.appblocker.AppListOuterClass.AppList
import com.appblocker.message.appListDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AppListRepository private constructor(context: Context) {
    private val _pm: PackageManager = context.packageManager
    private val _applicationInfo = _pm.getInstalledApplications(PackageManager.GET_META_DATA)
    private val _installedApplicationLabelList = flowOf(_applicationInfo.mapIndexedNotNull { index, appInfo ->
        App(
            label = _pm.getApplicationLabel(appInfo).toString(),
            icon = _pm.getApplicationIcon(appInfo),
            index = index,
        )
    })
    private val appListDataStore = context.appListDataStore

    val installedApplicationList: Flow<List<App>>
        get() {
            return _installedApplicationLabelList
        }

    val savedApplicationList: Flow<AppList>
        get() {
            return appListDataStore.data
        }

    // this is similar to having static methods in Java, but with more functionality.
    companion object {
        @Volatile
        private var INSTANCE: AppListRepository? = null

        fun getInstance(context: Context): AppListRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = AppListRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}