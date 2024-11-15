package com.bowoon.pokemon.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bowoon.pokemon.Log
import com.bowoon.pokemon.ui.vm.PokemonUiState
import com.bowoon.pokemon.ui.vm.PokemonVM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    viewModel: PokemonVM = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val state: PokemonUiState by viewModel.info.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = "getPokemonInfo") {
        scope.launch {
            viewModel.info
                .onEach { event ->
                    when (event) {
                        is PokemonUiState.Loading -> {}
                        is PokemonUiState.Success -> Log.d("PokemonInfo", event.data.toString())
                        is PokemonUiState.Error -> {}
                    }
                }.collect()
        }
    }
    PokemonDetailScreen(state)
}

@Composable
fun PokemonDetailScreen(state: PokemonUiState) {
    when (state) {
        is PokemonUiState.Loading -> Log.d("PokemonInfo", "Loading...")
        is PokemonUiState.Success -> {
            Log.d("PokemonInfo", state.data.toString())
            Text(text = state.data.name ?: "")
        }
        is PokemonUiState.Error -> Log.d("PokemonInfo", state.throwable.message.toString())
    }
}