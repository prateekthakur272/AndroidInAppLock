package dev.prateekthakur.androidinapplock.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import dev.prateekthakur.androidinapplock.data.store.SETTINGS_KEY
import dev.prateekthakur.androidinapplock.data.store.settingsStore
import dev.prateekthakur.androidinapplock.domain.models.AppSettingsData
import dev.prateekthakur.androidinapplock.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(private val context: Context) : SettingsRepository {

    private val gson = Gson()

    override suspend fun getSettings(): AppSettingsData {
        val json = context.settingsStore.data.first()[SETTINGS_KEY]
        val settings = gson.fromJson(json, AppSettingsData::class.java)
        return settings?: AppSettingsData()
    }

    override suspend fun setSettings(settings: AppSettingsData) {
        val json = gson.toJson(settings)
        context.settingsStore.edit { prefs ->
            prefs[SETTINGS_KEY] = json
        }
    }

    override suspend fun resetSettings() {
        val json = gson.toJson(AppSettingsData())
        context.settingsStore.edit { prefs ->
            prefs[SETTINGS_KEY] = json
        }
    }
}