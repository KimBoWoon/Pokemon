package com.bowoon.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.bowoon.common.RestartableStateFlow
import com.bowoon.common.Result
import com.bowoon.common.asResult
import com.bowoon.common.restartableStateIn
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.model.Evolution
import com.bowoon.model.PokemonStatus
import com.bowoon.pokemon.navigation.PokemonRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
) : ViewModel() {
    companion object {
        private const val TAG = "PokemonVM"
    }

    private val url = savedStateHandle.toRoute<PokemonRoute>().url

//    val pokemonUiState: StateFlow<PokemonUiState> = pokemonUiState(url, repository)
//        .stateIn(
//            scope = viewModelScope,
//            initialValue = PokemonUiState.Loading,
//            started = SharingStarted.WhileSubscribed(5000)
//        )
    val pokemonUiState: RestartableStateFlow<PokemonUiState> = pokemonUiState(url, repository)
        .restartableStateIn(
            scope = viewModelScope,
            initialValue = PokemonUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )
}

sealed interface PokemonUiState {
    data object Loading : PokemonUiState
    data class Success(val data: PokemonStatus) : PokemonUiState
    data class Error(val throwable: Throwable) : PokemonUiState
}

sealed interface PokemonEvolutionUiState {
    data object Loading : PokemonEvolutionUiState
    data class Success(val data: Evolution) : PokemonEvolutionUiState
    data class Error(val throwable: Throwable) : PokemonEvolutionUiState
}

private fun pokemonUiState(
    url: String,
    repository: PokemonRepository
): Flow<PokemonUiState> =
    repository.getPokemonInfo(url)
        .asResult()
        .map { ability ->
            when (ability) {
                is Result.Loading -> PokemonUiState.Loading
                is Result.Success -> PokemonUiState.Success(ability.data)
                is Result.Error -> PokemonUiState.Error(ability.exception)
            }
        }