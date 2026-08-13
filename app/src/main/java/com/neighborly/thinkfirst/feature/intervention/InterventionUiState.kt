package com.neighborly.thinkfirst.feature.intervention

data class InterventionUiState(
    val appName: String = "",
    val countDown: Int = 5,
    val isCountdownFinish: Boolean = false,
    val showDecision: Boolean = false,
    val packageName: String = "",
)