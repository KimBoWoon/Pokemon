package com.bowoon.domain

import com.bowoon.network.model.PokemonResponse
import com.bowoon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(url: String): Flow<PokemonResponse> = repository.getPokemonInfo(url)
}