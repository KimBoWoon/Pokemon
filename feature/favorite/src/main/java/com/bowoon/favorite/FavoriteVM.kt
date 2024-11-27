package com.bowoon.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bowoon.data.repository.FavoritePokemonRepository
import com.bowoon.domain.GetFavoritePokemonUseCase
import com.bowoon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val repository: FavoritePokemonRepository,
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "FavoriteVM"
    }

    val favoritePokemonUiState: StateFlow<FavoritePokemonUiState> = getFavoritePokemonUseCase()
        .map { FavoritePokemonUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = FavoritePokemonUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )
//    val favoritePokemonUiState: StateFlow<FavoriteUiState> =
//        repository.observeAllFavoritePokemon().map {
//            FavoriteUiState.Success(it)
//        }.stateIn(
//            scope = viewModelScope,
//            initialValue = FavoriteUiState.Loading,
//            started = SharingStarted.WhileSubscribed(5000)
//        )

    fun updateFavoritePokemon(pokemon: Pokemon) {
        viewModelScope.launch { repository.updateFavoritePokemon(pokemon) }
    }
}

sealed interface FavoritePokemonUiState {
    data object Loading : FavoritePokemonUiState
    data class Success(val list: List<Pokemon>) : FavoritePokemonUiState
}