package com.neighborly.thinkfirst.data.repository

import android.content.Context
import com.neighborly.thinkfirst.data.datasource.PackageManagerDataSource
import com.neighborly.thinkfirst.data.mapper.MyAppMapper
import com.neighborly.thinkfirst.domain.model.InstalledApp
import com.neighborly.thinkfirst.domain.repository.AppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val dataSource: PackageManagerDataSource,
    private val mapper: MyAppMapper
) : AppRepository {
    override suspend fun getInstalledApps(): List<InstalledApp> {
        return dataSource.getInstalledApplications().map { applicationInfo ->
            mapper.toInstalledApp(
                packageManager = context.packageManager,
                applicationInfo = applicationInfo
            )
        }.sortedBy { it.appName.lowercase() }
    }
}