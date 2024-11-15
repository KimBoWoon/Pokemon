package com.bowoon.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bowoon.common.dp100
import com.bowoon.imgloader.ImgLoader
import com.bowoon.network.model.Pokemon

@Composable
fun ListScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: ListVM = hiltViewModel()
) {
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
                pokemon?.let { PokemonItemView(it, onPokemonClick) }
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
    pokemon: Pokemon,
    onPokemonClick: (String) -> Unit,
) {
    Column (
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                pokemon.url?.let { url ->
                    onPokemonClick(url)
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