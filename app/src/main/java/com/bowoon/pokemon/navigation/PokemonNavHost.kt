package com.bowoon.pokemon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bowoon.favorite.navigation.favoriteScreen
import com.bowoon.list.navigation.ListBaseRoute
import com.bowoon.list.navigation.listSection
import com.bowoon.pokemon.PokemonAppState
import com.bowoon.setting.navigation.settingScreen

@Composable
fun PokemonNavHost(
    modifier: Modifier,
    appState: PokemonAppState,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = ListBaseRoute,
        modifier = modifier
    ) {
        listSection(
            onPokemonClick = navController::navigateToPokemon,
        ) {
            pokemonScreen()
        }
        favoriteScreen(onPokemonClick = navController::navigateToPokemon)
        settingScreen()
    }
}