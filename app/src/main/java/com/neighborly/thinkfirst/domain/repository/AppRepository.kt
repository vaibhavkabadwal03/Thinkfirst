package com.neighborly.thinkfirst.domain.repository

import com.neighborly.thinkfirst.domain.model.InstalledApp
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getInstalledApps(): List<InstalledApp>
    suspend fun addSelectedApp(packageName: String)

    suspend fun removeSelectedApp(packageName: String)
    fun observeSelectedApps(): Flow<Set<String>>

}