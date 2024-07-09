package com.appblocker

import TimePickerDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appblocker.ui.theme.AppBLockerTheme
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerSelector(modifier: Modifier = Modifier) {

    val time = Calendar.getInstance()
    time.timeInMillis = System.currentTimeMillis()

    // initialize start time state to current time
    val startTimePickerState = rememberTimePickerState(
        initialMinute = time[Calendar.MINUTE],
        initialHour = time[Calendar.HOUR]
    )

    // initialize end time state to current time
    val endTimePickerState = rememberTimePickerState(
        initialMinute = time[Calendar.MINUTE],
        initialHour = time[Calendar.HOUR]
    )

    Box {
        Surface {
            Column {
                Row {
                    Text(
                        text = "Current Start Time: " + startTimePickerState.hour + ":" + startTimePickerState.minute,
                        modifier = Modifier.padding(top = 12.dp, end = 4.dp)
                    )
                    Spacer(Modifier)
                    TimePickerWrapper(
                        title = "Select Start Time",
                        timePickerState = startTimePickerState
                    )
                }
                Row {
                    Text(
                        text = "Current End Time: " + endTimePickerState.hour + ":" + endTimePickerState.minute,
                        modifier = Modifier.padding(top = 12.dp, end = 8.dp)
                    )
                    TimePickerWrapper(
                        title = "Select End Time",
                        timePickerState = endTimePickerState
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerWrapper(
    title: String,
    timePickerState: TimePickerState,
    modifier: Modifier = Modifier
) {
    var showTimePicker by remember { mutableStateOf(false) }

    fun hideTimePicker() {
        showTimePicker = false
    }

    Button(onClick = { showTimePicker = true }) {
        Text(title)
    }

    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { hideTimePicker() },
            onConfirm = { hideTimePicker() }
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerSelectorPreview() {
    AppBLockerTheme {
        TimerSelector()
    }
}