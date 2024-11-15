package com.bowoon.pokemon.apis

import com.bowoon.pokemon.apis.data.Ability
import com.bowoon.pokemon.apis.data.PokemonResponse
import com.bowoon.pokemon.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(
        limit: Int = 20,
        offset: Int = 0
    ): ApiResponse<PokemonResponse>

    fun getPokemonInfo(
        url: String?
    ): Flow<Ability>
}