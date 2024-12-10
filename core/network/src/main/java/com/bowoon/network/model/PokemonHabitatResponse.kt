package com.bowoon.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonHabitatResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("names")
    val names: List<Name>? = null,
    @SerialName("pokemon_species")
    val pokemonSpecies: List<PokemonSpecy>? = null
)

@Serializable
data class PokemonSpecy(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)