package com.appblocker

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.appblocker.ui.theme.AppBLockerTheme
import kotlinx.coroutines.launch

@Composable
fun AppList(
    installedApplicationLabelList: List<AppRowData>,
    modifier: Modifier = Modifier
) {
    val TAG = "AppList";
    var loadingProgress by remember {
        mutableStateOf(0f)
    }

    if (installedApplicationLabelList.isEmpty()) {
        ProgressIndicator(progress = loadingProgress)
    } else {
        LazyColumn {
            items(installedApplicationLabelList) {
                    appRowData -> AppRow(appRowData)
            }
        }
    }
}

data class AppRowData(val label: String, val icon: Drawable)

@Composable
fun ProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize(1f)) {
        CircularProgressIndicator(
            color = Color.Cyan,
            progress = progress
        )
        Text(text = "Loading Application list...", modifier = Modifier.padding(top = 64.dp))
    }
}

@Composable
fun AppRow(
    data: AppRowData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Image(
            bitmap = data.icon.toBitmap().asImageBitmap(),
            contentDescription = data.label,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp, start = 4.dp)
        )
        Text(data.label, modifier = Modifier.padding(top = 12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    AppBLockerTheme {
        AppList(listOf())
    }
}