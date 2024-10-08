package com.appblocker.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Timer(
    selectedAppCount: Int,
    modifier: Modifier = Modifier
) {
    if (selectedAppCount == 0) {
        Text(text = "Please select apps to be blocked.")
    } else {
        Text(text = "You have selected %s apps".format(selectedAppCount))
        TimerSelector()
    }
}