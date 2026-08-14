package com.neighborly.thinkfirst.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.neighborly.thinkfirst.MainActivity
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
    private var ignoredPackage: String? = null
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
        openThinkFirst()
    }

    private fun openThinkFirst() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
            )
        }

        startActivity(intent)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString() ?: return

        if (packageName == lastForegroundPackage) {
            return
        }
        lastForegroundPackage = packageName

        Log.d(TAG, "Foreground app: $packageName")

        if (packageName in selectedPackages) {
            if (packageName == InterventionBypass.packageName) {
                Log.d(
                    TAG,
                    "Ignoring intervention for: $packageName"
                )
                InterventionBypass.packageName = null
                return
            }

            Log.d(TAG, "SELECTED APP DETECTED: $packageName")
            openInterventionActivity(packageName)
        } else {
            Log.d(TAG, "APP NOT SELECTED: $packageName")
        }

        // We'll connect the selected-app check here.
    }

    private fun openInterventionActivity(packageName: String) {
        val appName = packageManager
            .getApplicationInfo(packageName, 0)
            .loadLabel(packageManager)
            .toString()

        val intent = Intent(
            this,
            InterventionActivity::class.java
        ).apply {
            putExtra(
                InterventionActivity.EXTRA_APP_NAME,
                appName
            )

            putExtra(
                InterventionActivity.EXTRA_PACKAGE_NAME,
                packageName
            )

            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        Log.d(
            TAG,
            "Launching intervention: appName=$appName, packageName=$packageName"
        )
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