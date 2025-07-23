package dev.prateekthakur.androidinapplock.domain.repository

interface AppSpecificLock {
    suspend fun setPin(pin: String) : Unit
    suspend fun reset() : Unit
    suspend fun verify(pin: String) : Boolean
}