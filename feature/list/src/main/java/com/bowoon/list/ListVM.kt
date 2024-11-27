package com.bowoon.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bowoon.data.repository.FavoritePokemonRepository
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.domain.GetFavoritePokemonUseCase
import com.bowoon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val favoritePokemonRepository: FavoritePokemonRepository,
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "ListVM"
    }

    val pokemonPageFlow: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ) {
        ListSource(pokemonRepository)
    }.flow.cachedIn(viewModelScope)
    val favoritePokemonUiState: StateFlow<FavoritePokemonUiState> = getFavoritePokemonUseCase()
        .map { FavoritePokemonUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = FavoritePokemonUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )
//    val favoritePokemonUiState = favoritePokemonRepository.observeAllFavoritePokemon()
//        .map { FavoritePokemonUiState.Success(it) }
//        .stateIn(
//            scope = viewModelScope,
//            initialValue = FavoritePokemonUiState.Loading,
//            started = SharingStarted.WhileSubscribed(5000)
//        )

    fun updateFavoritePokemon(pokemon: Pokemon) {
        viewModelScope.launch { favoritePokemonRepository.updateFavoritePokemon(pokemon) }
    }
}

sealed interface FavoritePokemonUiState {
    data object Loading : FavoritePokemonUiState
    data class Success(val list: List<Pokemon>) : FavoritePokemonUiState
}