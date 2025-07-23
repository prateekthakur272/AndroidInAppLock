package dev.prateekthakur.androidinapplock.ui.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.prateekthakur.androidinapplock.R
import dev.prateekthakur.androidinapplock.domain.enums.AppLockType
import dev.prateekthakur.androidinapplock.ui.features.elements.CheckboxListItem
import dev.prateekthakur.androidinapplock.ui.features.elements.SwitchListItem
import dev.prateekthakur.androidinapplock.ui.features.elements.Watermark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = rememberSettingsViewModel()
) {
    val settings by settingsViewModel.state.collectAsState()

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = {
            Text("Settings")
        }, actions = {
            IconButton(onClick = {settingsViewModel.reset()}) {
                Icon(
                    painter = painterResource(R.drawable.outline_lock_reset_24),
                    contentDescription = "Reset"
                )
            }
        })
    }) { safeArea ->
        Box(modifier.padding(safeArea)) {
            LazyColumn {
                item {
                    SwitchListItem(
                        headline = "App Lock",
                        checked = settings.lockType != AppLockType.NONE,
                        subHeadline = "Secure app with app lock",
                        onCheckedChange = {
                            settingsViewModel.setLockType(if (it) AppLockType.SYSTEM_LOCK_SCREEN else AppLockType.NONE)
                        },
                    )
                }

                if (settings.lockType != AppLockType.NONE) {
                    item {
                        CheckboxListItem(
                            headline = "System",
                            subHeadline = "Use system lock screen",
                            checked = settings.lockType == AppLockType.SYSTEM_LOCK_SCREEN,
                            onCheckedChange = {
                                settingsViewModel.setLockType(AppLockType.SYSTEM_LOCK_SCREEN)
                            })
                        CheckboxListItem(
                            headline = "App Specific",
                            subHeadline = "Create app specific pin",
                            checked = settings.lockType == AppLockType.APP_SPECIFIC_LOCK,
                            onCheckedChange = {
                                settingsViewModel.setLockType(AppLockType.APP_SPECIFIC_LOCK)
                            })
                    }
                }

                item {
                    Watermark()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}