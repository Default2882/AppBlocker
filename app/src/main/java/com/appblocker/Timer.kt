package com.appblocker

import TimePickerDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appblocker.ui.theme.AppBLockerTheme
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Timer(modifier: Modifier = Modifier) {
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    
    val time = Calendar.getInstance()
    time.timeInMillis = System.currentTimeMillis()
    
    val startTimePickerState = rememberTimePickerState(
        initialMinute = time[Calendar.MINUTE],
        initialHour = time[Calendar.HOUR]
    )

    val endTimePickerState = rememberTimePickerState(
        initialMinute = time[Calendar.MINUTE],
        initialHour = time[Calendar.HOUR]
    )

    fun hideStartTimePicker() {
        showStartTimePicker = false
    }

    fun hideEndTimePicker() {
        showEndTimePicker = false
    }

    Box {
        Surface {
            Column {
                Button(onClick = { showStartTimePicker = true }) {
                    Text("Set Start Time")
                }
                Button(onClick = { showEndTimePicker = true }) {
                    Text("Set End Time")
                }
            }   
        }
    }

    if (showStartTimePicker) {
        TimePickerDialog(
            onCancel = { hideTimePicker() },
            onConfirm = { hideTimePicker() }
        ) {
            TimePicker(state = startTimePickerState)
        }
    }
}

@Composable
fun TimePicker(modifier: Modifier = Modifier) {
    
}

@Preview(showBackground = true)
@Composable
fun TimerPreview() {
    AppBLockerTheme {
        Timer()
    }
}