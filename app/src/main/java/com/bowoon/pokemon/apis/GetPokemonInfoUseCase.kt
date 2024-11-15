package com.bowoon.pokemon.apis

import com.bowoon.pokemon.apis.data.Ability
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(url: String): Flow<Ability> = repository.getPokemonInfo(url)
}