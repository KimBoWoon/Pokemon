package com.bowoon.pokemon.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bowoon.data.repository.UserDataRepository
import com.bowoon.setting.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    val userData: StateFlow<SettingUiState> =
        userDataRepository.userData.map {
            SettingUiState.Success(it)
        }.stateIn(
            scope = viewModelScope,
            initialValue = SettingUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )
}