package com.bowoon.data.repository

import com.bowoon.model.PokemonAbility
import com.bowoon.model.PokemonList
import com.bowoon.network.ApiResponse
import com.bowoon.network.model.asExternalModel
import com.bowoon.network.retrofit.Apis
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class PokemonRepositoryImpl @Inject constructor(
    private val apis: Apis
) : PokemonRepository {
    override suspend fun getPokemonList(url: String): PokemonList =
        when (val response = apis.pokemonApis.getPokemonList(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> response.data.asExternalModel()
        }

    override fun getPokemonInfo(url: String): Flow<PokemonAbility> = flow {
        when (val response = apis.pokemonApis.getPokemonInfo(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> emit(response.data.asExternalModel())
        }
    }
}