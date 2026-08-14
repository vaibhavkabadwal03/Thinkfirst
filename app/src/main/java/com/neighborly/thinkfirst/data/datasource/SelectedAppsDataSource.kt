package com.neighborly.thinkfirst.data.datasource

import kotlinx.coroutines.flow.Flow

interface SelectedAppsDataSource {

   /* suspend fun saveSelectedApps(
        packageNames: Set<String>
    )
*/

    suspend fun addSelectedApp(packageName: String)

    suspend fun removeSelectedApp(packageName: String)
    fun observeSelectedApps(): Flow<Set<String>>
}