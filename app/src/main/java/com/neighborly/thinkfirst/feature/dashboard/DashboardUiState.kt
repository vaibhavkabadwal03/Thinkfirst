package com.neighborly.thinkfirst.feature.dashboard

data class DashboardUiState(
    val monitoringEnabled: Boolean = false,
    val restrictedApps: Int = 0
)