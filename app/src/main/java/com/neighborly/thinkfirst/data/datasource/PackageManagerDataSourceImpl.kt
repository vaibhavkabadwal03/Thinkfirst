package com.neighborly.thinkfirst.data.datasource

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PackageManagerDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    PackageManagerDataSource {
    override suspend fun getLauncherApplications(): List<ApplicationInfo> =
        withContext(Dispatchers.IO) {
            val packageManager = context.packageManager
            val launcherIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }

            packageManager
                .queryIntentActivities(launcherIntent, 0)
                .map { resolveInfo ->
                    resolveInfo.activityInfo.applicationInfo
                }
                .distinctBy { applicationInfo ->
                    applicationInfo.packageName
                }
        }
}