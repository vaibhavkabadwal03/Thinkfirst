package com.neighborly.thinkfirst.service

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString()
            ?: return

        Log.d(
            TAG,
            "Foreground app: $packageName"
        )
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility service interrupted")
    }

    companion object {
        private const val TAG = "ThinkFirstAccessibility"
    }
}