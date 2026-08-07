package com.neighborly.thinkfirst.data.datasource

import android.content.Context
import android.content.pm.ApplicationInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PackageManagerDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    fun getInstalledApplications(): List<ApplicationInfo> {

        return context.packageManager
            .getInstalledApplications(0)

    }
}