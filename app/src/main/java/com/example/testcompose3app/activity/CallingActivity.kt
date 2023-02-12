package com.example.testcompose3app.activity

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.testcompose3app.ui.theme.TestCompose3AppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application()

@AndroidEntryPoint
class CallingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onClick = {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("myapp://testing.app?name=ThisIsName&age=12")
            startActivity(intent)
        }
        setContent {
            TestCompose3AppTheme {
                Button(onClick = onClick) {
                    Text(text = "Start")
                }
            }
        }
    }
}