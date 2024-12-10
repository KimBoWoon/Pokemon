package com.bowoon.data.repository

import com.bowoon.model.Evolution
import com.bowoon.model.PokemonStatus
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
    override fun getPokemonInfo(url: String): Flow<PokemonStatus> = flow {
        when (val response = apis.pokemonApis.getPokemonInfo(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> emit(response.data.asExternalModel())
        }
    }

    override fun getPokemonEvolution(url: String): Flow<Evolution> = flow {
        when (val response = apis.pokemonApis.getPokemonEvolution(url)) {
            is ApiResponse.Failure -> throw response.throwable
            is ApiResponse.Success -> emit(response.data.asExternalModel())
        }
    }
}