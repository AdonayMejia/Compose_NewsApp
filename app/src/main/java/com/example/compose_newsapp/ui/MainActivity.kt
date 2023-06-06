package com.example.compose_newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose_newsapp.model.navigation.MainScreen
import com.example.compose_newsapp.ui.theme.Compose_NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_NewsAppTheme {
                MainScreen()
            }
        }
    }
}
