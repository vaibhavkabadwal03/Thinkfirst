package com.example.thinkfirst.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun DashboardRoute(navController: NavHostController, viewModel: DashboardViewModel = hiltViewModel()) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreen(uiState = state.value)
}