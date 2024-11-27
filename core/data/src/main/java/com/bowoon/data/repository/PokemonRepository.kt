package com.bowoon.data.repository

import com.bowoon.model.PokemonAbility
import com.bowoon.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(
        url: String
    ): PokemonList

    fun getPokemonInfo(
        url: String
    ): Flow<PokemonAbility>
}