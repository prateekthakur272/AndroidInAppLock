package dev.prateekthakur.androidinapplock.ui.features.lockcontent

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import dev.prateekthakur.androidinapplock.domain.enums.AppLockType
import dev.prateekthakur.androidinapplock.ui.features.settings.SettingsViewModel
import dev.prateekthakur.androidinapplock.ui.features.settings.rememberSettingsViewModel

@Composable
fun SecuredContent(
    settingsViewModel: SettingsViewModel = rememberSettingsViewModel(),
    content: @Composable ()-> Unit,
) {
    val context = LocalContext.current
    val activity = context as FragmentActivity

    val settings by settingsViewModel.state.collectAsState()
    var isAuthenticated by remember { mutableStateOf(false) }

    LaunchedEffect(settings) {
        if (settings.lockType == AppLockType.NONE) {
            isAuthenticated = true
        } else if (settings.lockType == AppLockType.SYSTEM_LOCK_SCREEN) {
            authenticateWithSystemLock(
                activity = activity,
                onSuccess = { isAuthenticated = true },
                onFailed = {
                    isAuthenticated = false
                }
            )
        }
    }

    if(!isAuthenticated){
        AlertDialog(
            title = {
                Text("Application is locked")
            },
            text = {
                Text("Please unlock application")
            },
            onDismissRequest = {},
            confirmButton = {

            }
        )
    }

    content()
}
