package com.bowoon.network

import com.bowoon.network.model.PokemonStatusResponse
import com.bowoon.network.model.EvolutionResponse
import com.bowoon.network.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * 서버에 데이터를 요청하는 서비스
 */
interface PokemonApis {
    @GET
    suspend fun getPokemonList(
        @Url url: String
    ): ApiResponse<PokemonListResponse>

    @GET
    suspend fun getPokemonInfo(
        @Url url: String
    ): ApiResponse<PokemonStatusResponse>

    @GET
    suspend fun getPokemonEvolution(
        @Url url: String
    ): ApiResponse<EvolutionResponse>
}