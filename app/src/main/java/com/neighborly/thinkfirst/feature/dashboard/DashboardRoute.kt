package com.neighborly.thinkfirst.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DashboardRoute(
    onNavigateToAppSelection: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel(),
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreen(uiState = state, onManageAppsClick = onNavigateToAppSelection)
}