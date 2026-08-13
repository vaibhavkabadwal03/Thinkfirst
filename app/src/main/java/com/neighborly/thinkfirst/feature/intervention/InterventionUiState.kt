package com.neighborly.thinkfirst.feature.intervention

import androidx.compose.ui.graphics.ImageBitmap

data class InterventionUiState(
    val appName: String = "",
    val countDown: Int = 5,
    val isCountdownFinish: Boolean = false,
    val showDecision: Boolean = false,
    val packageName: String = "",
    val appIcon: ImageBitmap? = null,
)