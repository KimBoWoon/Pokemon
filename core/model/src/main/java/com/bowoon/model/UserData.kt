package com.bowoon.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val favoriteList: List<Pokemon> = emptyList(),
    val isDarkMode: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM
)