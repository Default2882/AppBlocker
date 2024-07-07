package com.appblocker

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appblocker.ui.theme.AppBLockerTheme

@Composable
fun App(
    mainAppViewModel: MainAppViewModel= viewModel(),
    modifier: Modifier = Modifier) {
    var selectedTab by remember {
        mutableStateOf(0)
    }
//    var installedApplicationLabelList by remember {
//        mutableStateOf(listOf<AppRowData>())
//    }

    val titles = listOf("Timer", "App List")
//    val context = LocalContext.current
//    val pm: PackageManager = context.packageManager
//    val applicationInfo = pm.getInstalledApplications(PackageManager.GET_META_DATA)
//
//    installedApplicationLabelList = applicationInfo.mapIndexedNotNull { index, appInfo ->
//        AppRowData(
//            label = pm.getApplicationLabel(appInfo).toString(),
//            icon = pm.getApplicationIcon(appInfo)
//        )
//    }.sortedBy { it.label }

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            titles.forEachIndexed { index, title -> Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = {
                    Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                })
            }
        }
        if (selectedTab == 1) {
            AppList(mainAppViewModel.getInstalledApplications())
        } else {
            TimerSelector()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppBLockerTheme {
        App()
    }
}