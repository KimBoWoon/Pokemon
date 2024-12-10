package com.bowoon.model


data class PokemonHabitat(
    val id: Int? = null,
    val name: String? = null,
    val names: List<Name>? = null,
    val pokemonSpecies: List<PokemonSpecy>? = null
)

data class PokemonSpecy(
    val name: String? = null,
    val url: String? = null
)