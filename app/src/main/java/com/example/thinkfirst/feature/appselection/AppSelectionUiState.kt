package com.example.thinkfirst.feature.appselection

import com.example.thinkfirst.domain.model.InstalledApp

data class AppSelectionUiState(

    val isLoading: Boolean = false,

    val installedApps: List<InstalledApp> = emptyList(),

    val error: String? = null
)