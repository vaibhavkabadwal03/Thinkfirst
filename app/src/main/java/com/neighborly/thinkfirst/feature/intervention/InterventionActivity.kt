package com.neighborly.thinkfirst.feature.intervention

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class InterventionActivity : ComponentActivity() {
    private val viewModel: InterventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appName = intent.getStringExtra(EXTRA_APP_NAME) ?: ""
        viewModel.startIntervention(appName)
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            InterventionScreen(
                state = state
            )
            Text(text = "Intervention Activity")
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)

        val appName = intent.getStringExtra(EXTRA_APP_NAME)
            ?: return

        viewModel.startIntervention(appName)
    }

    companion object {
        const val EXTRA_APP_NAME = "extra_app_name"
    }
}

