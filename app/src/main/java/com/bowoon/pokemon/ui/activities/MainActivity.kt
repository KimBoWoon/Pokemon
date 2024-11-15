package com.bowoon.pokemon.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.bowoon.pokemon.ui.screen.DetailScreen
import com.bowoon.pokemon.ui.screen.MainScreen
import com.bowoon.pokemon.ui.theme.PokemonTheme
import com.bowoon.pokemon.ui.theme.dp100
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

enum class Screen {
    Main,
    Detail
}

@Serializable
data object MainBaseRoute
@Serializable
data object MainRoute
@Serializable
data class PokemonRoute(val url: String? = null)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InitNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun InitNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainBaseRoute,
        modifier = modifier
    ) {
        navigation<MainBaseRoute>(startDestination = MainRoute) {
            composable<MainRoute>(
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            ) {
                MainScreen(navController)
            }
            composable<PokemonRoute>(
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                DetailScreen()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonTheme {

    }
}