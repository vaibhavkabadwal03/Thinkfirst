package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AppSelectionRoute(
    viewModel: AppSelectionViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    AppSelectionScreen(
        uiState = state.value
    )
}