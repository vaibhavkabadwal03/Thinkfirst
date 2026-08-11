package com.neighborly.thinkfirst.data.local

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.appDataStore by preferencesDataStore(
    name = "thinkfirst_preferences"
)

object AppPreferencesKeys {
    val SELECTED_APPS = stringSetPreferencesKey("selected_apps")
}