package com.neighborly.thinkfirst.data.mapper

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.neighborly.thinkfirst.domain.model.InstalledApp
import javax.inject.Inject

class MyAppMapper @Inject constructor() {

    fun toInstalledApp(
        packageManager: PackageManager,
        applicationInfo: ApplicationInfo
    ): InstalledApp {


        return InstalledApp(
            appName = applicationInfo.loadLabel(packageManager).toString(),
            packageName = applicationInfo.packageName
        )
    }
}