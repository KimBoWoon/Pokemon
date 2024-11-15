package com.bowoon.data.repository

import com.bowoon.network.model.PokemonListResponse
import com.bowoon.network.model.PokemonResponse
import com.bowoon.network.ApiResponse
import com.bowoon.network.retrofit.Apis
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class PokemonRepositoryImpl @Inject constructor(
    private val apis: Apis
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponse<PokemonListResponse> =
        apis.pokemonApis.getPokemonList(limit, offset)

    override fun getPokemonInfo(url: String?): Flow<PokemonResponse> = flow {
        if (url.isNullOrEmpty()) {
            throw RuntimeException("url is null or empty")
        }
        when (val response = apis.pokemonApis.getPokemonInfo(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> emit(response.data)
        }
    }
}