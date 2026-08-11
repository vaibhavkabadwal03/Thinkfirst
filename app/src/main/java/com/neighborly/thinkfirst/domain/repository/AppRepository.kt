package com.neighborly.thinkfirst.domain.repository

import com.neighborly.thinkfirst.domain.model.InstalledApp
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getInstalledApps(): List<InstalledApp>

    suspend fun saveSelectedApps(
        packageNames: Set<String>
    )

    fun observeSelectedApps(): Flow<Set<String>>

}