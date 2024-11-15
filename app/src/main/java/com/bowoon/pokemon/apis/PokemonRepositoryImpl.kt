package com.bowoon.pokemon.apis

import com.bowoon.pokemon.apis.data.Ability
import com.bowoon.pokemon.apis.data.PokemonResponse
import com.bowoon.pokemon.network.ApiResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class PokemonRepositoryImpl @Inject constructor(
    private val apis: Apis
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponse<PokemonResponse> =
        apis.pokemonApis.getPokemonList(limit, offset)

    override fun getPokemonInfo(url: String?): Flow<Ability> = flow {
        if (url.isNullOrEmpty()) {
            throw RuntimeException("url is null or empty")
        }
        when (val response = apis.pokemonApis.getPokemonInfo(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> emit(response.data)
        }
    }
}