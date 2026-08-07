package com.neighborly.thinkfirst.data.mapper

import android.content.Context
import android.content.pm.ApplicationInfo
import com.neighborly.thinkfirst.domain.model.InstalledApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyAppMapper @Inject constructor(@ApplicationContext private val context: Context) {
    fun toInstalledApp(applicationInfo: ApplicationInfo): InstalledApp {
        val packageManager = context.packageManager

        return InstalledApp(
            appName = applicationInfo.loadLabel(packageManager).toString(),
            packageName = applicationInfo.packageName,
            icon = applicationInfo.loadIcon(packageManager)
        )
    }
}