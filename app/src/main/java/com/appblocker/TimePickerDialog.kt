package com.appblocker

import android.widget.TimePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.appblocker.ui.theme.AppBLockerTheme

@Composable
fun TimePickerDialog(
    title: String = "Pick Your Time",
    onSubmit: () -> Unit,
    onDismiss: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column {
            Text(text = title)
            content()
            Row {
                TextButton(onClick = onDismiss) {
                    Text(text = "Dismiss")
                }
                TextButton(onClick = onSubmit) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TimePickerDialogPreview() {
    val state = rememberTimePickerState()
    AppBLockerTheme {
        TimePickerDialog("Pick Your Time", {}, {}) {
            TimePicker(state = state)
        }
    }
}