package com.bowoon.pokemon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bowoon.list.navigation.ListBaseRoute
import com.bowoon.list.navigation.listSection

@Composable
fun PokemonNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()

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
    }
}