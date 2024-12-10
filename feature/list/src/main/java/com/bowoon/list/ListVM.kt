package com.bowoon.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bowoon.data.repository.FavoritePokemonRepository
import com.bowoon.domain.GetFavoritePokemonUseCase
import com.bowoon.domain.GetPokemonListUseCase
import com.bowoon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val favoritePokemonRepository: FavoritePokemonRepository,
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "ListVM"
    }

    val pokemonPageFlow = getPokemonListUseCase.pokemonPageFlow.cachedIn(viewModelScope)
    val favoritePokemonUiState: StateFlow<FavoritePokemonUiState> = getFavoritePokemonUseCase()
        .map { FavoritePokemonUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = FavoritePokemonUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun updateFavoritePokemon(pokemon: Pokemon) {
        viewModelScope.launch { favoritePokemonRepository.updateFavoritePokemon(pokemon) }
    }
}

sealed interface FavoritePokemonUiState {
    data object Loading : FavoritePokemonUiState
    data class Success(val list: List<Pokemon>) : FavoritePokemonUiState
}