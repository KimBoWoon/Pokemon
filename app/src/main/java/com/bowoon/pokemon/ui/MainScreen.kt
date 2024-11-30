package com.bowoon.pokemon.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.bowoon.pokemon.PokemonAppState
import com.bowoon.pokemon.navigation.PokemonNavHost
import com.bowoon.ui.BottomNavigationBarItem
import com.bowoon.ui.BottomNavigationRailItem
import com.bowoon.ui.PokemonNavigationDefaults
import com.bowoon.ui.dp50
import kotlin.reflect.KClass

@Composable
fun MainScreen(
    appState: PokemonAppState,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { Navigation(appState = appState) }
    ) { innerPadding ->
        PokemonNavHost(
            modifier = Modifier.padding(innerPadding),
            appState = appState
        )
    }
}

@Composable
fun Navigation(
    appState: PokemonAppState
) {
    val windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val context = LocalContext.current
    val currentDestination = appState.currentDestination

    when (layoutType) {
        NavigationSuiteType.NavigationBar -> {
            NavigationBar(
                modifier = Modifier.fillMaxWidth().height(dp50),
                containerColor = PokemonNavigationDefaults.navigationContainerColor(),
                contentColor = PokemonNavigationDefaults.navigationContentColor()
            ) {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                    BottomNavigationBarItem(
                        selected = selected,
                        label = context.getString(destination.titleTextId),
                        selectedIcon = destination.selectedIcon,
                        unSelectedIcon = destination.unselectedIcon,
                        onClick = { appState.navigateToTopLevelDestination(destination) }
                    )
                }
            }
        }
        NavigationSuiteType.NavigationRail -> {
            NavigationRail(
                modifier = Modifier.width(dp50).fillMaxHeight(),
                containerColor = PokemonNavigationDefaults.navigationContainerColor(),
                contentColor = PokemonNavigationDefaults.navigationContentColor()
            ) {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                    BottomNavigationRailItem(
                        selected = selected,
                        label = context.getString(destination.titleTextId),
                        selectedIcon = destination.selectedIcon,
                        unSelectedIcon = destination.unselectedIcon,
                        onClick = { appState.navigateToTopLevelDestination(destination) }
                    )
                }
            }
        }
        NavigationSuiteType.NavigationDrawer -> {
            NavigationRail(
                modifier = Modifier.width(dp50).fillMaxHeight(),
                containerColor = PokemonNavigationDefaults.navigationContainerColor(),
                contentColor = PokemonNavigationDefaults.navigationContentColor()
            ) {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                    BottomNavigationRailItem(
                        selected = selected,
                        label = context.getString(destination.titleTextId),
                        selectedIcon = destination.selectedIcon,
                        unSelectedIcon = destination.unselectedIcon,
                        onClick = { appState.navigateToTopLevelDestination(destination) }
                    )
                }
            }
        }
        else -> {
            NavigationBar(
                modifier = Modifier.fillMaxWidth().height(dp50),
                containerColor = PokemonNavigationDefaults.navigationContainerColor(),
                contentColor = PokemonNavigationDefaults.navigationContentColor()
            ) {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                    BottomNavigationBarItem(
                        selected = selected,
                        label = context.getString(destination.titleTextId),
                        selectedIcon = destination.selectedIcon,
                        unSelectedIcon = destination.unselectedIcon,
                        onClick = { appState.navigateToTopLevelDestination(destination) }
                    )
                }
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false