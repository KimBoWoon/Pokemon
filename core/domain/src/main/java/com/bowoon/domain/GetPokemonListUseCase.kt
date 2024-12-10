package com.bowoon.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bowoon.data.paging.PokemonListPagingSource
import com.bowoon.model.Pokemon
import com.bowoon.network.retrofit.Apis
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val apis: Apis
) {
    val pokemonPageFlow: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ) {
        PokemonListPagingSource(apis = apis)
    }.flow
}