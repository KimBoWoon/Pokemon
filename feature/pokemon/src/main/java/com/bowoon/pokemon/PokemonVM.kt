package com.bowoon.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.bowoon.common.asResult
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.network.model.PokemonResponse
import com.bowoon.pokemon.navigation.PokemonRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PokemonDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
) : ViewModel() {
    companion object {
        private const val TAG = "PokemonVM"
    }

    val url = savedStateHandle.toRoute<PokemonRoute>().url
    val info: StateFlow<PokemonUiState> = pokemonUiState(url, repository)
        .stateIn(
            scope = viewModelScope,
            initialValue = PokemonUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )
}

sealed interface PokemonUiState {
    data object Loading : PokemonUiState
    data class Success(val data: PokemonResponse) : PokemonUiState
    data class Error(val throwable: Throwable) : PokemonUiState
}

private fun pokemonUiState(
    url: String?,
    repository: PokemonRepository
): Flow<PokemonUiState> =
    repository.getPokemonInfo(url)
        .asResult()
        .map { ability ->
            when (ability) {
                is com.bowoon.common.Result.Loading -> PokemonUiState.Loading
                is com.bowoon.common.Result.Success -> PokemonUiState.Success(ability.data)
                is com.bowoon.common.Result.Error -> PokemonUiState.Error(ability.exception)
            }
        }