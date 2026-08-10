package com.neighborly.thinkfirst.data.repository

import com.neighborly.thinkfirst.data.datasource.PackageManagerDataSource
import com.neighborly.thinkfirst.data.mapper.MyAppMapper
import com.neighborly.thinkfirst.domain.model.InstalledApp
import com.neighborly.thinkfirst.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val dataSource: PackageManagerDataSource, private val mapper: MyAppMapper) : AppRepository {
    override suspend fun getInstalledApps(): List<InstalledApp> =
        withContext(Dispatchers.IO) {

            dataSource
                .getLauncherApplications()
                .map(mapper::toInstalledApp)
                .sortedBy {
                    it.appName.lowercase()
                }
        }
}