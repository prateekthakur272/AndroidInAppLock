package dev.prateekthakur.androidinapplock.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import dev.prateekthakur.androidinapplock.data.store.PIN_KEY
import dev.prateekthakur.androidinapplock.data.store.lockDataStore
import dev.prateekthakur.androidinapplock.data.utils.CryptoUtils
import dev.prateekthakur.androidinapplock.domain.repository.AppSpecificLock
import kotlinx.coroutines.flow.first

class AppSpecificLockImpl(private val context: Context) : AppSpecificLock {
    override suspend fun setPin(pin: String) {
        context.lockDataStore.edit {
            it[PIN_KEY] = CryptoUtils.encrypt(pin)
        }
    }

    override suspend fun reset() {
        context.lockDataStore.edit {
            it.remove(PIN_KEY)
        }
    }

    override suspend fun verify(pin: String): Boolean {
        val encryptedPin = context.lockDataStore.data.first()[PIN_KEY] ?: return false
        return try {
            CryptoUtils.decrypt(encryptedPin) == pin
        } catch (e: Exception) {
            false
        }
    }
}