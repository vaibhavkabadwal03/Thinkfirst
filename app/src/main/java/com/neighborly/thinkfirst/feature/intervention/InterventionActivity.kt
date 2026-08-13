package com.neighborly.thinkfirst.feature.intervention

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.neighborly.thinkfirst.service.InterventionBypass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InterventionActivity : ComponentActivity() {
    private val viewModel: InterventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntent(intent)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        InterventionEffect.Close -> {
                            closeIntervention()
                        }

                        is InterventionEffect.OpenApp -> {
                            openApp(effect.packageName)
                        }
                    }

                }
            }
        }

        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            InterventionScreen(
                state = state,
                onOpenClick = viewModel::onOpenClick,
                onCloseClick = viewModel::onCloseClick
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)
        handleIntent(intent)
    }

    companion object {
        const val EXTRA_APP_NAME = "extra_app_name"
        const val EXTRA_PACKAGE_NAME = "extra_package_name"
    }

    private fun handleIntent(intent: Intent) {

        val appName = intent.getStringExtra(EXTRA_APP_NAME) ?: return

        val packageName = intent.getStringExtra(EXTRA_PACKAGE_NAME) ?: return

        viewModel.startIntervention(
            appName = appName,
            packageName = packageName
        )
    }

    private fun openApp(packageName: String) {
        InterventionBypass.packageName = packageName

        val launchIntent =
            packageManager.getLaunchIntentForPackage(packageName)

        if (launchIntent != null) {
            startActivity(launchIntent)
        }
        finish()
    }

    private fun closeIntervention() {
        val homeIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(homeIntent)
        finish()
    }
}

