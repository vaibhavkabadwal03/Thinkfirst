package com.neighborly.thinkfirst.feature.intervention

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun InterventionScreen(
    state: InterventionUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = state.appName,
            fontSize = 24.sp
        )

        Text(
            text = if (state.isCountdownFinish) {
                "Do you want to continue?"
            } else {
                state.countDown.toString()
            },
            fontSize = 48.sp
        )
    }
}