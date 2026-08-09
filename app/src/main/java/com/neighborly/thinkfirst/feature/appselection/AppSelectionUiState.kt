package com.neighborly.thinkfirst.feature.appselection

import com.neighborly.thinkfirst.domain.model.InstalledApp

data class AppSelectionUiState(
    val apps: List<InstalledApp> = emptyList(),
    val selectedPackages: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAccessibilityDialog: Boolean = false
)