package com.neighborly.thinkfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.core.view.WindowCompat
import com.neighborly.thinkfirst.core.navigation.AppNavGraph
import com.neighborly.thinkfirst.feature.permissions.AccessibilityPermissionChecker
import com.neighborly.thinkfirst.ui.theme.ThinkFirstTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val isAccessibilityEnabled = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.BLACK,
                android.graphics.Color.BLACK
            )
        )
        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = false
        isAccessibilityEnabled.value =
            AccessibilityPermissionChecker.isEnabled(this)
        setContent {
            ThinkFirstTheme {
                AppNavGraph(
                    isAccessibilityEnabled = isAccessibilityEnabled.value
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        isAccessibilityEnabled.value =
            AccessibilityPermissionChecker.isEnabled(this)
    }
}