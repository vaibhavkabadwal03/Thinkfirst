package com.neighborly.thinkfirst.data.datasource

import android.content.Context
import android.content.Intent
import com.neighborly.thinkfirst.data.model.LauncherAppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PackageManagerDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    PackageManagerDataSource {
    override suspend fun getLauncherApplications(): List<LauncherAppInfo> =
        withContext(Dispatchers.IO) {

            val packageManager = context.packageManager

            val launcherIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }

            val apps = packageManager.queryIntentActivities(launcherIntent, 0)

            val launcherApps = apps
                .map { resolveInfo ->
                    LauncherAppInfo(
                        appName = resolveInfo.loadLabel(packageManager).toString(),
                        packageName = resolveInfo
                            .activityInfo
                            .packageName
                    )
                }
                .distinctBy { it.packageName }

            return@withContext launcherApps

        }
}