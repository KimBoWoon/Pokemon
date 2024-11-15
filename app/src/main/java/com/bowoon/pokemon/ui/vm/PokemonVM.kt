package com.bowoon.pokemon.ui.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bowoon.pokemon.Result
import com.bowoon.pokemon.apis.GetPokemonInfoUseCase
import com.bowoon.pokemon.apis.PokemonApiSource
import com.bowoon.pokemon.apis.PokemonRepository
import com.bowoon.pokemon.apis.data.Pokemon
import com.bowoon.pokemon.asResult
import com.bowoon.pokemon.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository,
    private val pokemonInfoUseCase: GetPokemonInfoUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "PokemonVM"
    }

    val pokemonPageFlow: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ) {
        PokemonApiSource(repository)
    }.flow.cachedIn(viewModelScope)
}

sealed interface PokemonListUiState {
    data object Loading : PokemonListUiState
    data class Success(val data: List<Pokemon>) : PokemonListUiState
    data class Error(val throwable: Throwable) : PokemonListUiState
}

fun pokemonListUiState(
    repository: PokemonRepository
): Flow<PokemonListUiState> =
    flow {
        emit(repository.getPokemonList())
    }.asResult()
        .map {
            when (it) {
                is Result.Loading -> PokemonListUiState.Loading
                is Result.Success -> {
                    when (it.data) {
                        is ApiResponse.Failure -> PokemonListUiState.Error(it.data.throwable)
                        is ApiResponse.Success -> PokemonListUiState.Success(
                            it.data.data.results ?: emptyList()
                        )
                    }
                }

                is Result.Error -> PokemonListUiState.Error(it.exception)
            }
        }