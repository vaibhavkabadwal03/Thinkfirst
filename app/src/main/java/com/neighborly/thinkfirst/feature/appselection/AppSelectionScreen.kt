package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.runtime.Composable

@Composable
fun AppSelectionScreen(
    uiState: AppSelectionUiState,
    onAppSelectionChanged: (String, Boolean) -> Unit
) {

    AppSelectionContent(uiState, onAppSelectionChanged)
}