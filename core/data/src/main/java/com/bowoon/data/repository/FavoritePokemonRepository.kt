package com.bowoon.data.repository

import com.bowoon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface FavoritePokemonRepository {
    fun observeAllFavoritePokemon(): Flow<List<Pokemon>>
    suspend fun updateFavoritePokemon(pokemon: Pokemon)
}