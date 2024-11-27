package com.bowoon.pokemon.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.bowoon.favorite.navigation.FavoriteRoute
import com.bowoon.list.navigation.ListBaseRoute
import com.bowoon.list.navigation.ListRoute
import com.bowoon.setting.navigation.SettingRoute
import kotlin.reflect.KClass

/**
 * 앱 최상단 내비게이션 바
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    LIST(
        selectedIcon = Icons.AutoMirrored.Rounded.List,
        unselectedIcon = Icons.AutoMirrored.Rounded.List,
        iconTextId = com.bowoon.list.R.string.feature_list_name,
        titleTextId = com.bowoon.list.R.string.feature_list_name,
        route = ListRoute::class,
        baseRoute = ListBaseRoute::class,
    ),
    FAVORITE(
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Rounded.FavoriteBorder,
        iconTextId = com.bowoon.favorite.R.string.feature_favorite_name,
        titleTextId = com.bowoon.favorite.R.string.feature_favorite_name,
        route = FavoriteRoute::class,
    ),
    SETTING(
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Rounded.Settings,
        iconTextId = com.bowoon.setting.R.string.feature_setting_name,
        titleTextId = com.bowoon.setting.R.string.feature_setting_name,
        route = SettingRoute::class,
    )
}
