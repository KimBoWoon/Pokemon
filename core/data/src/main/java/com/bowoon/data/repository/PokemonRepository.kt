package com.bowoon.data.repository

import com.bowoon.network.model.PokemonListResponse
import com.bowoon.network.model.PokemonResponse
import com.bowoon.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(
        limit: Int = 20,
        offset: Int = 0
    ): ApiResponse<PokemonListResponse>

    fun getPokemonInfo(
        url: String?
    ): Flow<PokemonResponse>
}