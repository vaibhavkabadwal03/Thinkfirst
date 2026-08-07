package com.neighborly.thinkfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neighborly.thinkfirst.core.navigation.AppNavGraph
import com.neighborly.thinkfirst.ui.theme.ThinkFirstTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkFirstTheme {
                AppNavGraph()
            }
        }
    }
}