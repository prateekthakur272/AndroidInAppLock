package dev.prateekthakur.androidinapplock

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import dev.prateekthakur.androidinapplock.ui.features.lockcontent.SecuredContent
import dev.prateekthakur.androidinapplock.ui.features.settings.SettingsScreen
import dev.prateekthakur.androidinapplock.ui.theme.AndroidInAppLockTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidInAppLockTheme {
                SecuredContent{
                    SettingsScreen()
                }
            }
        }
    }
}
