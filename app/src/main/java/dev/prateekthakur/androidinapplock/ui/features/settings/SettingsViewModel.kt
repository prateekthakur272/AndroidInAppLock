package dev.prateekthakur.androidinapplock.ui.features.settings

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.prateekthakur.androidinapplock.data.repository.SettingsRepositoryImpl
import dev.prateekthakur.androidinapplock.domain.enums.AppLockType
import dev.prateekthakur.androidinapplock.domain.models.AppSettingsData
import dev.prateekthakur.androidinapplock.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {
    private val mutableState = MutableStateFlow(AppSettingsData())
    val state = mutableState.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            val settings = repository.getSettings()
            Log.d("SettingsViewModel", "load: $settings")
            mutableState.update { settings }
        }
    }

    fun setLockType(lockType: AppLockType){
        val settings = state.value.copy(lockType = lockType)
        viewModelScope.launch {
            repository.setSettings(settings)
            mutableState.update { settings }
        }
    }

    fun reset(){
        viewModelScope.launch {
            repository.resetSettings()
            load()
        }
    }
}


@Composable
fun rememberSettingsViewModel(): SettingsViewModel {
    val context = LocalContext.current
    return viewModel(
        factory = viewModelFactory {
            initializer {
                SettingsViewModel(SettingsRepositoryImpl(context))
            }
        }
    )
}