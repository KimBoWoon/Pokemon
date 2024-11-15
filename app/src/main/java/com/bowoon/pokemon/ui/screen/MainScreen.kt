package com.bowoon.pokemon.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bowoon.pokemon.apis.data.Pokemon
import com.bowoon.pokemon.imgLoader.ImgLoader
import com.bowoon.pokemon.ui.activities.AppendErrorView
import com.bowoon.pokemon.ui.activities.AppendLoadingView
import com.bowoon.pokemon.ui.activities.PokemonRoute
import com.bowoon.pokemon.ui.vm.PokemonVM

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: PokemonVM = hiltViewModel()
) {
//    val scope = rememberCoroutineScope()
//    val pokemonList = remember { mutableStateOf<List<Pokemon>?>(null) }
//    LaunchedEffect(
//        key1 = "loadPokemon"
//    ) {
//        scope.launch {
//            viewModel.event.onEach { event ->
//                when (event) {
//                    is PokemonListUiState.Loading -> Log.d("PokemonList", "loading...")
//                    is PokemonListUiState.Success -> {
//                        Log.d("PokemonList", event.data.toString())
//                        pokemonList.value = event.data
//                    }
//                    is PokemonListUiState.Error -> Log.d("PokemonList", event.throwable.message.toString())
//                }
//            }.collect()
//        }
//    }
//
//    LazyColumn(
//        modifier = Modifier
//    ) {
//        items(
//            items = pokemonList.value ?: emptyList(),
//            key = { it }
//        ) { pokemon ->
//            PokemonItemView(navController, pokemon)
//        }
//    }
    val pager = remember { viewModel.pokemonPageFlow }
    val lazyPagingItems = pager.collectAsLazyPagingItems()

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

        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = lazyPagingItems.itemCount
            ) { index ->
                val pokemon = lazyPagingItems[index]
                pokemon?.let { PokemonItemView(navController, it) }
            }

            item {
                when (lazyPagingItems.loadState.append) {
                    is LoadState.Loading -> AppendLoadingView()
                    is LoadState.NotLoading -> {}
                    is LoadState.Error -> AppendErrorView { lazyPagingItems.retry() }
                }
            }
        }
    }
}

@Composable
fun PokemonItemView(
    navController: NavController,
    pokemon: Pokemon
) {
    Column (
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                pokemon.url?.let { url ->
                    navController.navigate(route = PokemonRoute(url))
                }
            }
    ) {
        ImgLoader.getImgLoader(LocalContext.current)
            .AsyncImageLoad(pokemon.getImageUrl())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            maxLines = 1,
            text = pokemon.name ?: ""
        )
    }
}