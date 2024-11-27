package com.bowoon.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bowoon.model.Pokemon
import com.bowoon.ui.PokemonItem
import com.bowoon.ui.TitleView
import com.bowoon.ui.dp100
import com.bowoon.ui.dp15
import com.bowoon.ui.dp16
import com.bowoon.ui.dp50

@Composable
fun ListScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: ListVM = hiltViewModel()
) {
    val pager = remember { viewModel.pokemonPageFlow }
    val lazyPagingItems = pager.collectAsLazyPagingItems()
    val state by viewModel.favoritePokemonUiState.collectAsStateWithLifecycle()

    ListScreen(
        lazyPagingItems = lazyPagingItems,
        state = state,
        onPokemonClick = onPokemonClick,
        updateFavoritePokemon = viewModel::updateFavoritePokemon
    )
}

@Composable
fun ListScreen(
    lazyPagingItems: LazyPagingItems<Pokemon>,
    state: FavoritePokemonUiState,
    onPokemonClick: (String) -> Unit,
    updateFavoritePokemon: (Pokemon) -> Unit
) {
    var favoriteList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }

    when (state) {
        is FavoritePokemonUiState.Loading -> {}
        is FavoritePokemonUiState.Success -> favoriteList = state.list
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (lazyPagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            is LoadState.NotLoading, is LoadState.Error -> {}
        }

        Column {
            TitleView(
                modifier = Modifier.fillMaxWidth()
                    .height(dp50),
                title = "Pokemon List"
            )
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(dp16),
                horizontalArrangement = Arrangement.spacedBy(dp15),
                verticalArrangement = Arrangement.spacedBy(dp15)
            ) {
                items(
                    count = lazyPagingItems.itemCount
                ) { index ->
                    lazyPagingItems[index]?.let { pokemon ->
                        PokemonItem(
                            pokemon = pokemon,
                            isFavorite = favoriteList.contains(pokemon),
                            onPokemonClick = onPokemonClick,
                            updateFavoritePokemon = updateFavoritePokemon
                        )
                    }
                }

                item(
                    span = { GridItemSpan(2) }
                ) {
                    when (lazyPagingItems.loadState.append) {
                        is LoadState.Loading -> AppendLoadingView()
                        is LoadState.NotLoading -> {}
                        is LoadState.Error -> AppendErrorView { lazyPagingItems.retry() }
                    }
                }
            }
        }
    }
}

@Composable
fun AppendLoadingView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dp100),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
        )
    }
}

@Composable
fun AppendErrorView(retry: () -> Unit?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dp100),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .wrapContentSize(),
            onClick = { retry() }
        ) {
            Text(text = "재시도")
        }
    }
}