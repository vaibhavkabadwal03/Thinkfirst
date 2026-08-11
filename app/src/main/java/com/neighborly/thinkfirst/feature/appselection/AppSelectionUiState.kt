package com.neighborly.thinkfirst.feature.appselection

import com.neighborly.thinkfirst.domain.model.InstalledApp

data class AppSelectionUiState(
    val apps: List<InstalledApp> = emptyList(),
    val selectedPackages: Set<String> = emptySet(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAccessibilityDialog: Boolean = false,
    val appFilter: AppFilter = AppFilter.ALL,
)

enum class AppFilter {
    ALL,
    SELECTED
}