package com.bowoon.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bowoon.common.Log
import com.bowoon.ui.TitleView
import com.bowoon.ui.dp50
import com.bowoon.ui.image.DynamicAsyncImageLoader

@Composable
fun PokemonScreen(
    viewModel: PokemonVM = hiltViewModel()
) {
    val state: PokemonUiState by viewModel.pokemonUiState.collectAsStateWithLifecycle()

    PokemonScreen(state)
}

@Composable
fun PokemonScreen(state: PokemonUiState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is PokemonUiState.Loading -> {
                Log.d("PokemonInfo", "Loading...")
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is PokemonUiState.Success -> {
                Log.d("PokemonInfo", state.data.toString())
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                ) {
                    TitleView(
                        modifier = Modifier.fillMaxWidth()
                            .height(dp50),
                        title = "Pokemon Ability"
                    )
                    DynamicAsyncImageLoader(
                        source = state.data.getImageUrl(),
                        contentDescription = "PokemonImageLoad",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = state.data.name ?: "")
                    state.data.stats?.forEach { stat ->
                        Text(text = stat.stat?.name.toString())
                        Text(text = stat.baseStat.toString())
                    }
                }
            }
            is PokemonUiState.Error -> Log.e("PokemonInfo", state.throwable.message.toString())
        }
    }
}