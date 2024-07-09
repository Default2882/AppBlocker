package com.appblocker

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appblocker.ui.theme.AppBLockerTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AppList(
    mainAppViewModel: MainAppViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val TAG = "AppList"
    var loadingProgress by remember {
        mutableStateOf(0f)
    }
    val installedApplicationLabelList = mainAppViewModel.getInstalledApplications()
    if (installedApplicationLabelList.isEmpty()) {
        ProgressIndicator(progress = loadingProgress)
    } else {
        LazyColumn {
            items(installedApplicationLabelList) { appRowData ->
                AppRow(appRowData)
            }
        }
    }
}

class AppRowState(val label: String, val icon: Drawable, var checked: Boolean = false) {
    fun onCheck() {
        this.checked = !this.checked
    }
}

@Composable
fun ProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize(1f)
    ) {
        CircularProgressIndicator(
            color = Color.Cyan,
            progress = progress
        )
        Text(text = "Loading Application list...", modifier = Modifier.padding(top = 64.dp))
    }
}

@Composable
fun AppRow(
    rowStateFlow: StateFlow<AppRowState>,
    modifier: Modifier = Modifier
) {
    val state by rowStateFlow.collectAsState()

    Row(
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                bitmap = state.icon.toBitmap().asImageBitmap(),
                contentDescription = state.label,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp, start = 4.dp)
            )
            Text(state.label, modifier = Modifier.padding(top = 12.dp))
        }
        Checkbox(checked = state.checked, onCheckedChange = { state.onCheck() })
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    AppBLockerTheme {
        AppList()
    }
}