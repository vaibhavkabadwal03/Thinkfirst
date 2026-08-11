package com.neighborly.thinkfirst.data.datasource

import kotlinx.coroutines.flow.Flow

interface SelectedAppsDataSource {

    suspend fun saveSelectedApps(
        packageNames: Set<String>
    )

    fun observeSelectedApps(): Flow<Set<String>>
}