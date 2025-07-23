package dev.prateekthakur.androidinapplock.data.store

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.lockDataStore by preferencesDataStore("app_lock_store")
val PIN_KEY = stringPreferencesKey("encrypted_pin")