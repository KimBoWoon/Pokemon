package com.bowoon.pokemon.apis

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bowoon.pokemon.apis.data.Pokemon
import com.bowoon.pokemon.network.ApiResponse
import javax.inject.Inject

class PokemonApiSource @Inject constructor(
    private val repository: PokemonRepository
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> =
        when (val response = repository.getPokemonList(params.loadSize, params.key ?: 0)) {
            is ApiResponse.Failure -> LoadResult.Error(response.throwable)
            is ApiResponse.Success -> {
                LoadResult.Page(
                    data = response.data.results ?: listOf(),
                    prevKey = null, // Only paging forward.
                    nextKey = if (response.data.next != null) params.loadSize + (params.key ?: 0) else null
                )
            }
        }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        // prevKet == null -> 첫 번째 페이지
        // nextKey == null -> 마지막 페이지
        // prevKey == null && nextKey == null -> 최초 페이지
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}