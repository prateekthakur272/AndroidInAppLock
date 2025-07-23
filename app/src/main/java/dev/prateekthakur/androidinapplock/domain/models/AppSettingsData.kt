package dev.prateekthakur.androidinapplock.domain.models

import dev.prateekthakur.androidinapplock.domain.enums.AppLockType

data class AppSettingsData(
    val lockType: AppLockType = AppLockType.NONE
)