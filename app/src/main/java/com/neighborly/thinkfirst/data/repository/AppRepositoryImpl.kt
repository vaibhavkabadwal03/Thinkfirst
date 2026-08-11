package com.neighborly.thinkfirst.data.repository

import com.neighborly.thinkfirst.data.datasource.PackageManagerDataSource
import com.neighborly.thinkfirst.data.datasource.SelectedAppsDataSource
import com.neighborly.thinkfirst.data.mapper.MyAppMapper
import com.neighborly.thinkfirst.domain.model.InstalledApp
import com.neighborly.thinkfirst.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val dataSource: PackageManagerDataSource, private val mapper: MyAppMapper, private val selectedAppsDataSource: SelectedAppsDataSource) : AppRepository {
    override suspend fun getInstalledApps(): List<InstalledApp> =
        withContext(Dispatchers.IO) {

            dataSource
                .getLauncherApplications()
                .map(mapper::toInstalledApp)
                .sortedBy {
                    it.appName.lowercase()
                }
        }

    override suspend fun saveSelectedApps(packageNames: Set<String>) {
        selectedAppsDataSource.saveSelectedApps(packageNames)
    }

    override fun observeSelectedApps(): Flow<Set<String>> {
        return selectedAppsDataSource.observeSelectedApps()
    }
}