package com.neighborly.thinkfirst.feature.dashboard

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.neighborly.thinkfirst.R

@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    onManageAppsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Monitoring: ${
                if (uiState.monitoringEnabled)
                    "ON"
                else
                    "OFF"
            }",
            color = MaterialTheme.colorScheme.primary

        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Restricted Apps: ${uiState.restrictedApps}",
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onManageAppsClick() }
        ) {
            Text("Manage Apps")
        }

    }
}