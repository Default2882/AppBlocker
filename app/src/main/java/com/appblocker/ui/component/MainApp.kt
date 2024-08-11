package com.appblocker.ui.component

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appblocker.ui.theme.AppBLockerTheme

@Composable
fun App(
    mainAppViewModel: MainAppViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var selectedTab by remember {
        mutableStateOf(0)
    }
    val titles = listOf("Timer", "App List")

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    })
            }
        }
        if (selectedTab == 1) {
            AppList(
                mainAppViewModel.getInstalledApplications(),
                {index -> mainAppViewModel.onCheck(index)}
            )
        } else {
            Timer(selectedAppCount = mainAppViewModel.getSelectedApplicationListCount())
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