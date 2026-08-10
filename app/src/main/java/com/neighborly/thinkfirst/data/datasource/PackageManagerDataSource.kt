package com.neighborly.thinkfirst.data.datasource

import android.content.pm.ApplicationInfo
import com.neighborly.thinkfirst.data.model.LauncherAppInfo

interface PackageManagerDataSource {
    suspend fun getLauncherApplications(): List<LauncherAppInfo>
}