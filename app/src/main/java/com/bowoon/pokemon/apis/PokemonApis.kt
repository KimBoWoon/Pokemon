package com.bowoon.pokemon.apis

import com.bowoon.pokemon.apis.data.Ability
import com.bowoon.pokemon.apis.data.PokemonResponse
import com.bowoon.pokemon.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * 서버에 데이터를 요청하는 서비스
 */
interface PokemonApis {
    @GET("/api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>

    @GET
    suspend fun getPokemonInfo(
        @Url url: String
    ): ApiResponse<Ability>
}