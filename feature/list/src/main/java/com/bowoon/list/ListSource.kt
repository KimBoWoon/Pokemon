package com.bowoon.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bowoon.common.Log
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.model.Pokemon
import javax.inject.Inject

class ListSource @Inject constructor(
    private val repository: PokemonRepository
) : PagingSource<String, Pokemon>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pokemon> =
        runCatching {
            val response = repository.getPokemonList(params.key ?: "https://pokeapi.co/api/v2/pokemon")

            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = null,
                nextKey = response.next
            )
        }.getOrElse { e ->
            Log.printStackTrace(e)
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<String, Pokemon>): String? {
        // prevKet == null -> 첫 번째 페이지
        // nextKey == null -> 마지막 페이지
        // prevKey == null && nextKey == null -> 최초 페이지
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }
}