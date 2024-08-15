package com.appblocker.ui.component

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appblocker.AppListOuterClass.AppList
import com.appblocker.data.App
import com.appblocker.data.AppListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

class MainAppViewModel(
    appListRepository: AppListRepository
) : ViewModel() {
    private val TAG = "MainAppViewModel"
    private val installedApplicationListFlow = appListRepository.installedApplicationList
    private val savedApplicationListFlow = appListRepository.savedApplicationList
    private val appRowDataListUiFlow = combine(
        installedApplicationListFlow,
        savedApplicationListFlow
    ) { installedAppList: List<App>, savedAppList: AppList ->
        val savedAppNameList = savedAppList.appNameList.map { appNameObject -> appNameObject.appName }
        Log.d(TAG, "Saved App Name List size: " + savedAppNameList.size.toString())
        installedAppList.mapIndexed { index, installedApp ->
            val isChecked = savedAppNameList.contains(installedApp.label)
            AppRowState(
                label = installedApp.label,
                index = index,
                icon = installedApp.icon,
                checked = isChecked
            )
        }
    }

    fun getInstalledApplications(): Flow<List<AppRowState>> {
        return appRowDataListUiFlow
    }

    fun onCheck(index: Int) {
//        appRowDataListUiFlow.
    }

//    fun getSelectedApplicationList(): List<StateFlow<AppRowState>> {
//        return _installedApplicationLabelList.filter { appRowData -> appRowData.value.checked }
//    }

    fun getSelectedApplicationListCount(): Int {
//        return getSelectedApplicationList().size
        return 0
    }
}