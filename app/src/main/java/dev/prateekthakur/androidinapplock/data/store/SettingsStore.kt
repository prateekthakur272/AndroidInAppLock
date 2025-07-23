package dev.prateekthakur.androidinapplock.data.store

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.settingsStore by preferencesDataStore("settings_data_store")
val SETTINGS_KEY = stringPreferencesKey("lock_type")
