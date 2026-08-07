package com.neighborly.thinkfirst.domain.repository

import com.neighborly.thinkfirst.domain.model.InstalledApp

interface AppRepository {
    suspend fun getInstalledApps(): List<InstalledApp>

}