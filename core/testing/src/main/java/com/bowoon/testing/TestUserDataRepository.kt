package com.bowoon.testing

import com.bowoon.data.repository.UserDataRepository
import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.Pokemon
import com.bowoon.model.UserData
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull

val emptyUserData = UserData(
    favoriteList = emptyList(),
    isDarkMode = DarkThemeConfig.FOLLOW_SYSTEM
)

class TestUserDataRepository : UserDataRepository {
    private val _userData = MutableSharedFlow<UserData>(replay = 1, onBufferOverflow = DROP_OLDEST)
    private val currentUserData get() = _userData.replayCache.firstOrNull() ?: emptyUserData
    override val userData: Flow<UserData> = _userData.filterNotNull()

    override suspend fun updateDarkModeTheme(config: DarkThemeConfig) {
        currentUserData.let { current ->
            _userData.tryEmit(current.copy(isDarkMode = config))
        }
    }

    override suspend fun updateFavoritePokemon(pokemon: Pokemon) {
        currentUserData.let { current ->
            _userData.tryEmit(current.copy(favoriteList = listOf(pokemon)))
        }
    }

    /**
     * only test function
     */
    fun setUserData(userData: UserData) {
        _userData.tryEmit(userData)
    }
}