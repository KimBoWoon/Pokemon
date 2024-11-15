package com.bowoon.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bowoon.common.Log

@Composable
fun PokemonScreen(
    viewModel: PokemonDetailVM = hiltViewModel()
) {
    val state: PokemonUiState by viewModel.info.collectAsStateWithLifecycle()

    PokemonScreen(state)
}

@Composable
fun PokemonScreen(state: PokemonUiState) {
    when (state) {
        is PokemonUiState.Loading -> Log.d("PokemonInfo", "Loading...")
        is PokemonUiState.Success -> {
            Log.d("PokemonInfo", state.data.toString())
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Text(text = state.data.name ?: "")
                state.data.stats?.forEach { stat ->
                    Text(text = stat.stat?.name.toString())
                    Text(text = stat.baseStat.toString())
                }
            }
        }
        is PokemonUiState.Error -> Log.d("PokemonInfo", state.throwable.message.toString())
    }
}