package com.example.thinkfirst.domain.repository

import com.example.thinkfirst.domain.model.InstalledApp

interface AppRepository {
    suspend fun getInstalledApps(): List<InstalledApp>

}