package com.example.thinkfirst.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "OneSec Clone",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Monitoring: ${
                if (uiState.monitoringEnabled)
                    "ON"
                else
                    "OFF"
            }"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Restricted Apps: ${uiState.restrictedApps}"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {}
        ) {
            Text("Manage Apps")
        }

    }
}