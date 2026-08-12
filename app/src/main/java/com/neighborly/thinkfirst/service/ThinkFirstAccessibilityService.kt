package com.neighborly.thinkfirst.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.neighborly.thinkfirst.domain.usecase.ObserveSelectedAppsUseCase
import com.neighborly.thinkfirst.feature.intervention.InterventionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ThinkFirstAccessibilityService : AccessibilityService() {

    @Inject
    lateinit var observeSelectedApps: ObserveSelectedAppsUseCase
    private var selectedPackages: Set<String> = emptySet()
    private var lastForegroundPackage: String? = null
    private val serviceScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )

    override fun onServiceConnected() {
        super.onServiceConnected()

        serviceScope.launch {
            observeSelectedApps().collectLatest { packages ->
                selectedPackages = packages

                Log.d(TAG, "Selected packages: $packages")
            }
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString()
            ?: return

        if (packageName == lastForegroundPackage) {
            return
        }
        lastForegroundPackage = packageName

        Log.d(TAG, "Foreground app: $packageName")

        if (packageName in selectedPackages) {
            Log.d(TAG, "SELECTED APP DETECTED: $packageName")
            openInterventionActivity()
        } else {
            Log.d(TAG, "APP NOT SELECTED: $packageName")
        }

        // We'll connect the selected-app check here.
    }

    private fun openInterventionActivity() {
        val intent = Intent(
            this,
            InterventionActivity::class.java
        ).apply {
            putExtra(
                InterventionActivity.EXTRA_APP_NAME,
                packageName
            )
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility service interrupted")
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "ThinkFirstAccessibility"
    }
}