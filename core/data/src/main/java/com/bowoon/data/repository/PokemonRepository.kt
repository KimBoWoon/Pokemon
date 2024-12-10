package com.bowoon.data.repository

import com.bowoon.model.Evolution
import com.bowoon.model.PokemonStatus
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonInfo(
        url: String
    ): Flow<PokemonStatus>

    fun getPokemonEvolution(
        url: String
    ): Flow<Evolution>
}