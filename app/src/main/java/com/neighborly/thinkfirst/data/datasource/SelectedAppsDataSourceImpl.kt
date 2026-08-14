package com.neighborly.thinkfirst.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.neighborly.thinkfirst.data.local.AppPreferencesKeys
import com.neighborly.thinkfirst.data.local.appDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SelectedAppsDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SelectedAppsDataSource {

    override suspend fun addSelectedApp(packageName: String) {
        context.appDataStore.edit { preferences ->
            val currentApps =
                preferences[AppPreferencesKeys.SELECTED_APPS]
                    ?: emptySet()

            preferences[AppPreferencesKeys.SELECTED_APPS] =
                currentApps + packageName
        }
    }

    override suspend fun removeSelectedApp(packageName: String) {
        context.appDataStore.edit { preferences ->
            val currentApps =
                preferences[AppPreferencesKeys.SELECTED_APPS]
                    ?: emptySet()

            preferences[AppPreferencesKeys.SELECTED_APPS] =
                currentApps - packageName
        }
    }

    override fun observeSelectedApps(): Flow<Set<String>> {
        return context.appDataStore.data.map { preferences ->
            preferences[AppPreferencesKeys.SELECTED_APPS]
                ?: emptySet()
        }
    }
}