package com.bowoon.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.network.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    companion object {
        private const val TAG = "PokemonVM"
    }

    val pokemonPageFlow: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ) {
        ListSource(repository)
    }.flow.cachedIn(viewModelScope)
}