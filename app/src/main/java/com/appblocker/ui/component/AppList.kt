package com.appblocker.ui.component

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.flow.StateFlow


val TAG = "AppList"

@Composable
fun AppList(
    applicationList: List<StateFlow<AppRowState>>,
    onCheck: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        itemsIndexed(applicationList) {index, appRowData ->
            AppRow(
                appRowData,
                { onCheck(index) }
            )
        }
    }
}

data class AppRowState(
    val label: String,
    val icon: Drawable,
    val index: Int,
    var checked: Boolean = false
)

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
    appRowStateFlow: StateFlow<AppRowState>,
    onCheck: () -> Unit,
    modifier: Modifier = Modifier
) {
    val appRowState by appRowStateFlow.collectAsState()
    Row(
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                bitmap = appRowState.icon.toBitmap().asImageBitmap(),
                contentDescription = appRowState.label,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp, start = 4.dp)
            )
            Text(appRowState.label, modifier = Modifier.padding(top = 12.dp))
        }
        Checkbox(checked = appRowState.checked, onCheckedChange = { isChecked -> onCheck() })
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppListPreview() {
//    AppBLockerTheme {
//        AppList()
//    }
//}