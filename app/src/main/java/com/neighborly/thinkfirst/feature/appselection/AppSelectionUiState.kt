package com.neighborly.thinkfirst.feature.appselection

import com.neighborly.thinkfirst.domain.model.InstalledApp

data class AppSelectionUiState(

    val isLoading: Boolean = false,

    val installedApps: List<InstalledApp> = emptyList(),

    val error: String? = null
)