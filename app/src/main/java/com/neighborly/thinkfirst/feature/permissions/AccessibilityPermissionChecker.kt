package com.neighborly.thinkfirst.feature.permissions

import android.content.Context
import android.provider.Settings

object AccessibilityPermissionChecker {

    fun isEnabled(context: Context): Boolean {
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        val expectedService = "${context.packageName}/com.neighborly.thinkfirst.service.ThinkFirstAccessibilityService"


        return enabledServices
            .split(":")
            .any { it.equals(expectedService, ignoreCase = true) }
    }
}