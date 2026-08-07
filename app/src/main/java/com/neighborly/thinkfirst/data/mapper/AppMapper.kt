package com.neighborly.thinkfirst.data.mapper

import android.content.Context
import android.content.pm.ApplicationInfo
import com.neighborly.thinkfirst.domain.model.InstalledApp
import javax.inject.Inject

class AppMapper @Inject constructor() {

    fun toInstalledApp(
        context: Context, applicationInfo: ApplicationInfo
    ): InstalledApp {

        val packageManager = context.packageManager

        return InstalledApp(
            appName = applicationInfo.loadLabel(packageManager).toString(),
            packageName = applicationInfo.packageName
        )
    }
}