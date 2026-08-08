package com.neighborly.thinkfirst.data.datasource

import android.content.Context
import android.content.pm.ApplicationInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PackageManagerDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context) : PackageManagerDataSource {
    override suspend fun getInstalledApplications(): List<ApplicationInfo> =
        withContext(Dispatchers.IO) {
            context.packageManager.getInstalledApplications(0)
        }
}