package com.bowoon.network

import com.bowoon.network.model.PokemonListResponse
import com.bowoon.network.model.PokemonResponse
import retrofit2.http.GET
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
    ): ApiResponse<PokemonListResponse>

    @GET
    suspend fun getPokemonInfo(
        @Url url: String
    ): ApiResponse<PokemonResponse>
}