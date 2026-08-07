package com.example.thinkfirst.data.repository

import android.content.Context
import com.example.thinkfirst.data.datasource.PackageManagerDataSource
import com.example.thinkfirst.data.mapper.AppMapper
import com.example.thinkfirst.domain.model.InstalledApp
import com.example.thinkfirst.domain.repository.AppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSource: PackageManagerDataSource,
    private val mapper: AppMapper
) : AppRepository {
    override suspend fun getInstalledApps(): List<InstalledApp> {
        return dataSource.getInstalledApplications().map { applicationInfo ->
            mapper.toInstalledApp(
                context = context,
                applicationInfo = applicationInfo
            )
        }.sortedBy { it.appName.lowercase() }
    }
}