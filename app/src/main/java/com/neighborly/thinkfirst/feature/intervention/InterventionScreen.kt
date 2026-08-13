package com.neighborly.thinkfirst.feature.intervention

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.R

@Composable
fun InterventionScreen(
    state: InterventionUiState,
    onOpenClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.app_core_phrase),
            fontSize = 24.sp
        )

        if (state.isCountdownFinish) {
            Text(text = "Do you want to open ${state.appName}?")
            Row {
                Button(
                    onClick = onCloseClick
                ) {
                    Text("Close")
                }

                Button(
                    onClick = onOpenClick
                ) {
                    Text("Open")
                }
            }
        } else {
            Text(text = state.countDown.toString())
        }
    }
}