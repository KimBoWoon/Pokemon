package com.bowoon.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String? = null,
    val url: String? = null
) {
    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}