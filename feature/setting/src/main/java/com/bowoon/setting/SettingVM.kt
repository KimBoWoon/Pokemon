package com.bowoon.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bowoon.data.repository.UserDataRepository
import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingVM @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    companion object {
        private const val TAG = "SettingVM"
    }

    val userDataUiState: StateFlow<SettingUiState> =
        userDataRepository.userData.map {
            SettingUiState.Success(it)
        }.stateIn(
            scope = viewModelScope,
            initialValue = SettingUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun updateDarkTheme(config: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.updateDarkModeTheme(config)
        }
    }
}

sealed interface SettingUiState {
    data object Loading : SettingUiState
    data class Success(val data: UserData) : SettingUiState
    data class Error(val throwable: Throwable) : SettingUiState
}