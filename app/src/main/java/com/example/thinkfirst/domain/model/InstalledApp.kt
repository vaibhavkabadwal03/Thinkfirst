package com.example.thinkfirst.domain.model


data class InstalledApp(
    val appName: String,
    val packageName: String,
    val isSelected: Boolean = false

)
