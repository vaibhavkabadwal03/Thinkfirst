package com.neighborly.thinkfirst.data.mapper

import com.neighborly.thinkfirst.data.model.LauncherAppInfo
import com.neighborly.thinkfirst.domain.model.InstalledApp
import javax.inject.Inject

class MyAppMapper @Inject constructor() {
    fun toInstalledApp(launcherAppInfo: LauncherAppInfo): InstalledApp {

        return InstalledApp(
            appName = launcherAppInfo.appName,
            packageName = launcherAppInfo.packageName,
        )
    }
}