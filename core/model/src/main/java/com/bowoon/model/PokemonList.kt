package com.bowoon.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokemon>? = null
)