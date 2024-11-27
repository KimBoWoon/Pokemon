package com.bowoon.data.repository

import com.bowoon.datastore.PokemonDataSource
import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.Pokemon
import com.bowoon.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val datastore: PokemonDataSource
) : UserDataRepository {
    override val userData: Flow<UserData> = datastore.userData

    override suspend fun updateDarkModeTheme(config: DarkThemeConfig) {
        datastore.updateDarkTheme(config)
    }

    override suspend fun updateFavoritePokemon(pokemon: Pokemon) {
        datastore.updateFavoritePokemon(pokemon)
    }
}