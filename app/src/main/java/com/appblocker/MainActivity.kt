package com.appblocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.appblocker.data.AppListRepository
import com.appblocker.ui.component.App
import com.appblocker.ui.component.MainAppViewModel
import com.appblocker.ui.theme.AppBLockerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBLockerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        mainAppViewModel = MainAppViewModel(AppListRepository
                            .getInstance(this)),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}