package com.bowoon.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bowoon.common.Log
import com.bowoon.model.Pokemon
import com.bowoon.network.ApiResponse
import com.bowoon.network.model.asExternalModel
import com.bowoon.network.retrofit.Apis
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val apis: Apis
) : PagingSource<String, Pokemon>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pokemon> =
        runCatching {
            when (val response = apis.pokemonApis.getPokemonList(params.key ?: "https://pokeapi.co/api/v2/pokemon")) {
                is ApiResponse.Failure -> LoadResult.Error(response.throwable)
                is ApiResponse.Success -> LoadResult.Page(
                    data = response.data.results?.asExternalModel() ?: emptyList(),
                    prevKey = null,
                    nextKey = response.data.next
                )
            }
        }.getOrElse { e ->
            Log.printStackTrace(e)
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<String, Pokemon>): String? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
}