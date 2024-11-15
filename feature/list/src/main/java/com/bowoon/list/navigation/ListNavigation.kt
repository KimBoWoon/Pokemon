package com.bowoon.list.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bowoon.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
data object ListRoute

@Serializable
data object ListBaseRoute

fun NavController.navigateToList(navOptions: NavOptions) = navigate(route = ListRoute, navOptions)

fun NavGraphBuilder.listSection(
    onPokemonClick: (String) -> Unit,
    pokemonDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<ListBaseRoute>(startDestination = ListRoute) {
        composable<ListRoute>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            ListScreen(onPokemonClick)
        }
        pokemonDestination()
    }
}
