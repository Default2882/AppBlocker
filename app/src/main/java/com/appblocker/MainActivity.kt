package com.appblocker

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.appblocker.ui.theme.AppBLockerTheme
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBLockerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val TAG = "MyActivity";
        Log.d(TAG, (LocalContext != null).toString())
        Log.d(TAG, (LocalContext.current != null).toString())

        val pm: PackageManager = LocalContext.current.packageManager

        val installedApplicationLabelList = pm.getInstalledApplications(PackageManager.GET_META_DATA).mapNotNull {
            appInfo ->
                try {
                    packageManager.getApplicationLabel(appInfo).toString()
                } catch (e: Exception) {
//                    Log.e(TAG, "Error getting label for app: ${appInfo.packageName}", e)
                    null
                }
        }
        Log.d(TAG, installedApplicationLabelList.size.toString())
        LazyColumn {
            items(installedApplicationLabelList) {
                label -> Text(label)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AppBLockerTheme {
            Greeting("Android")
        }
    }
}