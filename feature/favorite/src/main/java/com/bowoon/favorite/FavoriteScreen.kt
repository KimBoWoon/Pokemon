package com.bowoon.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bowoon.model.Pokemon
import com.bowoon.ui.PokemonItem
import com.bowoon.ui.TitleView
import com.bowoon.ui.dp15
import com.bowoon.ui.dp50

@Composable
fun FavoriteScreen(
    viewModel: FavoriteVM = hiltViewModel(),
    onPokemonClick: (String) -> Unit
) {
    val favoritePokemonUiState by viewModel.favoritePokemonUiState.collectAsStateWithLifecycle()

    FavoriteScreen(
        state = favoritePokemonUiState,
        onPokemonClick = onPokemonClick,
        updateFavoritePokemon = viewModel::updateFavoritePokemon
    )
}

@Composable
fun FavoriteScreen(
    state: FavoritePokemonUiState,
    onPokemonClick: (String) -> Unit,
    updateFavoritePokemon: (Pokemon) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TitleView(
            modifier = Modifier.fillMaxWidth()
                .height(dp50),
            title = "Favorite Pokemon"
        )
        when (state) {
            is FavoritePokemonUiState.Loading -> CircularProgressIndicator()
            is FavoritePokemonUiState.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(dp15),
                    horizontalArrangement = Arrangement.spacedBy(dp15),
                    verticalArrangement = Arrangement.spacedBy(dp15)
                ) {
                    items(
                        items = state.list
                    ) { pokemon ->
                        PokemonItem(
                            pokemon = pokemon,
                            isFavorite = state.list.contains(pokemon),
                            onPokemonClick = onPokemonClick,
                            updateFavoritePokemon = updateFavoritePokemon
                        )
                    }
                }
            }
        }
    }
}