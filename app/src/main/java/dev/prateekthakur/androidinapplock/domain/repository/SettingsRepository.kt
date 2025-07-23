package dev.prateekthakur.androidinapplock.domain.repository

import dev.prateekthakur.androidinapplock.domain.models.AppSettingsData

interface SettingsRepository {
    suspend fun getSettings() : AppSettingsData
    suspend fun setSettings(settings: AppSettingsData)
    suspend fun resetSettings()
}