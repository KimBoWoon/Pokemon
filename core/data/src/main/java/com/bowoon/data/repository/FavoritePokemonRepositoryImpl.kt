package com.bowoon.data.repository

import com.bowoon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritePokemonRepositoryImpl @Inject constructor(
    private val userDataRepository: UserDataRepository
) : FavoritePokemonRepository {
    override fun observeAllFavoritePokemon(): Flow<List<Pokemon>> =
        userDataRepository.userData.map { it.favoriteList }.flatMapLatest { favoritePokemon ->
                when {
                    favoritePokemon.isEmpty() -> flowOf(emptyList())
                    else -> flowOf(favoritePokemon)
                }
            }

    override suspend fun updateFavoritePokemon(pokemon: Pokemon) {
        userDataRepository.updateFavoritePokemon(pokemon)
    }
}