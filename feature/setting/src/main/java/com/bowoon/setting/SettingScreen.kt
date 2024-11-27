package com.bowoon.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.UserData
import com.bowoon.ui.TitleView
import com.bowoon.ui.dp50

@Composable
fun SettingScreen(
    viewModel: SettingVM = hiltViewModel()
) {
    val state by viewModel.userDataUiState.collectAsStateWithLifecycle()

    SettingScreen(
        state = state,
        updateDarkTheme = viewModel::updateDarkTheme
    )
}

@Composable
fun SettingScreen(
    state: SettingUiState,
    updateDarkTheme: (DarkThemeConfig) -> Unit
) {
    var userData by remember { mutableStateOf<UserData>(UserData()) }

    when (state) {
        is SettingUiState.Loading -> {}
        is SettingUiState.Success -> userData = state.data
        is SettingUiState.Error -> {}
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TitleView(
            modifier = Modifier.fillMaxWidth()
                .height(dp50),
            title = "Setting"
        )
        Row() {
            Text(text = "Setting")
            Switch(
                checked = when (userData.isDarkMode) {
                    DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
                    DarkThemeConfig.LIGHT -> false
                    DarkThemeConfig.DARK -> true
                },
                onCheckedChange = { isDarkMode ->
                    updateDarkTheme(
                        when (isDarkMode) {
                            true -> DarkThemeConfig.DARK
                            false -> DarkThemeConfig.LIGHT
                        }
                    )
                }
            )
        }
    }
}