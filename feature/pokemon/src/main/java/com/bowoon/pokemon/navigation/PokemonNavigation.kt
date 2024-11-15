package com.bowoon.pokemon.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.bowoon.pokemon.PokemonScreen
import kotlinx.serialization.Serializable

@Serializable
data class PokemonRoute(val url: String)

fun NavController.navigateToPokemon(topicId: String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = PokemonRoute(topicId)) {
        navOptions()
    }
}

fun NavGraphBuilder.pokemonScreen(
) {
    composable<PokemonRoute>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        PokemonScreen()
    }
}