package com.example.formapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.formapp.ui.form.FormScreen
import com.example.formapp.ui.theme.FormAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormAppTheme {
                val context = LocalContext.current
                FormScreen(context = context)
            }
        }
    }
}