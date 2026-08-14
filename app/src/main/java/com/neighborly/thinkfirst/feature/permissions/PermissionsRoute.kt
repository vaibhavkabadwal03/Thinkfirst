package com.neighborly.thinkfirst.feature.permissions

import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionsRoute() {

    val context = LocalContext.current

    PermissionsScreen(
        onEnableAccessibilityClick = {
            context.startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        }
    )
}