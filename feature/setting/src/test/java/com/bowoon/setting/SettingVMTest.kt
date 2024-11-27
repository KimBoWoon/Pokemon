package com.bowoon.setting

import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.Pokemon
import com.bowoon.model.UserData
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestUserDataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SettingVMTest {
    /**
     * 테스트 환경설정
     */
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val userDataRepository = TestUserDataRepository()
    private lateinit var viewModel: SettingVM

    @Before
    fun setup() {
        viewModel = SettingVM(userDataRepository)
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(SettingUiState.Loading, viewModel.userDataUiState.value)
    }

    @Test
    fun stateIsSuccessAfterUserDataLoaded() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.userDataUiState.collect() }

        userDataRepository.updateDarkModeTheme(DarkThemeConfig.DARK)

        assertEquals(
            SettingUiState.Success(
                UserData(
                    isDarkMode = DarkThemeConfig.DARK,
                    favoriteList = emptyList(),
                ),
            ),
            viewModel.userDataUiState.value,
        )
    }

    @Test
    fun setUserData() = runTest {
        val userData = UserData(
            favoriteList = listOf(Pokemon("name1", "url1"))
        )
        userDataRepository.setUserData(userData)

        assertEquals(
            userData,
            userDataRepository.userData.first()
        )
    }
}