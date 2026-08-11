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

    override suspend fun saveSelectedApps(
        packageNames: Set<String>
    ) {
        context.appDataStore.edit { preferences ->
            preferences[AppPreferencesKeys.SELECTED_APPS] = packageNames
        }
    }

    override fun observeSelectedApps(): Flow<Set<String>> {
        return context.appDataStore.data.map { preferences ->
            preferences[AppPreferencesKeys.SELECTED_APPS]
                ?: emptySet()
        }
    }
}