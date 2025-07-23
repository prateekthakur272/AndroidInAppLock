package dev.prateekthakur.androidinapplock.data.utils

import android.annotation.SuppressLint
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object CryptoUtils {

    private const val SECRET = "SuperSecretKeyForPIN"
    private fun getKey(): SecretKey {
        val md = MessageDigest.getInstance("SHA-256")
        val keyBytes = md.digest(SECRET.toByteArray())
        return SecretKeySpec(keyBytes, "AES")
    }

    @SuppressLint("GetInstance")
    fun encrypt(pin: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        val encrypted = cipher.doFinal(pin.toByteArray())
        return android.util.Base64.encodeToString(encrypted, android.util.Base64.DEFAULT)
    }

    @SuppressLint("GetInstance")
    fun decrypt(encryptedPin: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, getKey())
        val decodedBytes = android.util.Base64.decode(encryptedPin, android.util.Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    }
}