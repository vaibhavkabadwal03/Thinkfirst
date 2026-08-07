package com.neighborly.thinkfirst.data.datasource

import android.content.pm.ApplicationInfo

interface PackageManagerDataSource {
    suspend fun getInstalledApplications(): List<ApplicationInfo>
}